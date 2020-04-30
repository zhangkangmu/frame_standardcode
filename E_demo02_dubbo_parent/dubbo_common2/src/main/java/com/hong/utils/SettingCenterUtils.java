package com.hong.utils;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Properties;

/**
 * @ClassName SettingCenterUtils
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/28 10:19
 * @Version V1.0
 */
// 读取Zookeeper中当配置（操作Zookeeper的java客户端CuratorFramework）
// 程序放弃从jdbc.properties文件读取数据库的配置，而从 Zookeeper中读取
public class SettingCenterUtils extends PropertyPlaceholderConfigurer implements ApplicationContextAware{

    // 只要加载spring容器，程序创建SettingCenterUtils，创建之后，就会执行processProperties方法，
    // 从Zookeeper中将连接数据库的4个配置，读取出来，放置到Properties props属性中，往后传递，程序读取从Zookeeper中的连接数据库信息
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        // 一：实现配置中心，将Zookeeper中的信息读取出来放置到props中
        loadZk(props);
        // 二 ：添加watch机制，实时监听配置中心中的数据变化，一旦有数据变化，重新载人最新的Zookeeper中的配置信息，放置到props中
        // 并重新加载spring容器，让配置生效
        addWatch(props);
        super.processProperties(beanFactoryToProcess, props);
    }

    // 二 ：添加watch机制，实时监听配置中心中的数据变化，一旦有数据变化，重新载人最新的Zookeeper中的配置信息，放置到props中
    // 并重新加载spring容器，让配置生效
    private void addWatch(Properties props) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",3000,3000,retryPolicy);
        client.start();

        /**
         * 监听的内容
         *   /config/driver  “com.mysql.jdbc.Driver”
             /config/url  “jdbc:mysql://localhost:3306/itcastdubbo”
             /config/user “root”
             /config/password  “root”
         */
        TreeCache treeCache = new TreeCache(client,"/config");
        try {
            treeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        treeCache.getListenable().addListener(new TreeCacheListener() {
            // 只要/config路径中的配置数据发生变化，就会执行childEvent方法
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                // 判断，更新节点和子节点数据的时候触发if
                if(event.getType() == TreeCacheEvent.Type.NODE_UPDATED){
//                    if("/config/driver".equals(event.getData().getPath())){
//                        props.setProperty("jdbc.driver",new String(event.getData().getData()));
//                    }
//                    else if("/config/url".equals(event.getData().getPath())){
//                        props.setProperty("jdbc.url",new String(event.getData().getData()));
//                    }
//                    else if("/config/user".equals(event.getData().getPath())){
//                        props.setProperty("jdbc.user",new String(event.getData().getData()));
//                    }
//                    else if("/config/password".equals(event.getData().getPath())){
//                        props.setProperty("jdbc.password",new String(event.getData().getData()));
//                    }
                    // 重新加载spring容器，避免重新启动web.xml，再去加载spring容器
                    // 获取容器对象，刷新spring容器
                    applicationContext.refresh();
                }
            }
        });


        // （注意）添加监听，不需要关闭client
    }

    // 一：实现配置中心，将Zookeeper中的信息读取出来放置到props中
    private void loadZk(Properties props) {
        // 编写载入Zookeeper的配置文件，传递到Properties属性中
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",3000,3000,retryPolicy);
        client.start();
        /**
         * <property name="driverClassName" value="${jdbc.driver}"></property>
           <property name="url" value="${jdbc.url}"></property>
           <property name="username" value="${jdbc.user}"></property>
           <property name="password" value="${jdbc.password}"></property>
         注意Property中的key和上面spring容器加载数据源的${jdbc.driver}一致
         */
        try {
            byte[] bytesDriver = client.getData().forPath("/config/driver");
            props.setProperty("jdbc.driver",new String(bytesDriver));
            byte[] bytesUrl = client.getData().forPath("/config/url");
            props.setProperty("jdbc.url",new String(bytesUrl));
            byte[] bytesUser = client.getData().forPath("/config/user");
            props.setProperty("jdbc.user",new String(bytesUser));
            byte[] bytesPassword = client.getData().forPath("/config/password");
            props.setProperty("jdbc.password",new String(bytesPassword));
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }

    // 使用容器对象
    XmlWebApplicationContext applicationContext;
    // 获取spring的容器对象，通过set方法的形参获取
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (XmlWebApplicationContext)applicationContext;
    }
}
