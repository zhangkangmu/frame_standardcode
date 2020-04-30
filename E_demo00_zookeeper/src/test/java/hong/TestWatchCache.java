package hong;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

/**
 * @ClassName TestCurator
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/26 9:29
 * @Version V1.0
 */
public class TestWatchCache {

    /**
     * 3.4.2、 NodeCache
        - 介绍
        NodeCache是用来监听节点的数据变化的，当监听的节点的数据发生变化的时候就会回调对应的函数。
     * @throws Exception
     */
    @Test
    public void testNodeCache() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy);
        // 开启客户端连接对象
        client.start();
        // NodeCache（监听和缓存根节点变化）
        /**
         * new NodeCache(client,"/a");
         *  参数1：连接的客户端对象（指定监听的服务器）
         *  参数2：监听的节点路径
         */
        NodeCache nodeCache = new NodeCache(client,"/a");
        // 启动监听
        /**
         * 参数为true：可以直接获取监听的节点，System.out.println(nodeCache.getCurrentData());为ChildData{path='/aa', stat=607,765,1580205779732,1580973376268,2,1,0,0,5,1,608
         , data=[97, 98, 99, 100, 101]}
         * 参数为false：不可以获取监听的节点，System.out.println(nodeCache.getCurrentData());为null
         */
        nodeCache.start(true);
        System.out.println(nodeCache.getCurrentData());
        // 监听根节点的变化
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            // 表示如果被监听的根节点数据发生变化，就会执行该方法
            @Override
            public void nodeChanged() throws Exception {
                // 获取路径
                String path = nodeCache.getPath();
                // 获取数据
                byte[] dataByte = nodeCache.getCurrentData().getData();
                String data = new String(dataByte);
                System.out.println("当前路径是:"+path+"        数据是："+data);
            }
        });


        // 注意：当前junit测试的线程不能关闭，否则不能进行watch监听机制（后续的时候，web工程完成监听，只要web工程的服务器不停止工作，一直就会进行监听Zookeeper节点数据的变化）
        System.in.read();
        // 关闭客户端连接对象（注意：如果关闭，不能进行watch监听机制）
        // client.close();
    }

    /**
     * 3.4.3、 PathChildrenCache
     - 介绍
     PathChildrenCache是用来监听指定节点 的子节点变化情况
     */
    @Test
    public void testPathChildrenCache() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy);
        // 开启客户端连接对象
        client.start();
        // PathChildrenCache（监听和缓存子节点变化）
        /**
         * new PathChildrenCache()
         *  CuratorFramework client：连接的客户端对象
         *  String path, 监听的路径
         *  boolean cacheData：显示数据的状态
         *    true表示用于配置是否把节点内容缓存起来
         *    如果配置为true，客户端在接收到节点列表变更的同时，也能够获取到节点的数据内容（即：event.getData().getData()）ͺ
         *    如果为false 则无法取到数据内容（即：event.getData().getData()）
         */
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client,"/a",true);
        // 启动监听
        /**
         * NORMAL:  普通启动方式, 在启动时缓存子节点数据
         * POST_INITIALIZED_EVENT：在启动时缓存子节点数据，提示初始化
         * BUILD_INITIAL_CACHE: 在启动时什么都不会输出
         *  在官方解释中说是因为这种模式会在start执行执行之前先执行rebuild的方法，而rebuild的方法不会发出任何事件通知。
         */
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        System.out.println(pathChildrenCache.getCurrentData()); // 查看当前节点的数据
        // 添加监听
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            // 监听子节点的数据变化，如果子节点的数据发生变化，就会执行该方法
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                // 使用事件的类型，判断执行的是子节点的哪些变化
                if(event.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED){
                    System.out.println("子节点添加，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == PathChildrenCacheEvent.Type.CHILD_UPDATED){
                    System.out.println("子节点修改，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED){
                    System.out.println("子节点删除，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == PathChildrenCacheEvent.Type.INITIALIZED){
                    System.out.println("初始化，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == PathChildrenCacheEvent.Type.CONNECTION_SUSPENDED){
                    System.out.println("连接超时，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == PathChildrenCacheEvent.Type.CONNECTION_RECONNECTED){
                    System.out.println("重试连接成功，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == PathChildrenCacheEvent.Type.CONNECTION_LOST){
                    System.out.println("连接断开丢失，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
            }
        });

        // 注意：当前junit测试的线程不能关闭，否则不能进行watch监听机制（后续的时候，web工程完成监听，只要web工程的服务器不停止工作，一直就会进行监听Zookeeper节点数据的变化）
        System.in.read();
        // 关闭客户端连接对象（注意：如果关闭，不能进行watch监听机制）
        // client.close();
    }

    /**
     * 3.4.4、TreeCache
     - 介绍
     TreeCache有点像上面两种Cache的结合体，NodeCache能够监听自身节点的数据变化（或者是创建该节点），PathChildrenCache能够监听自身节点下的子节点的变化，而TreeCache既能够监听自身节点的变化、也能够监听子节点的变化。
     */
    @Test
    public void testTreeCache() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy);
        // 开启客户端连接对象
        client.start();
        // TreeCache（监听和缓存根、子节点变化）
        /**
         *  new TreeCache(CuratorFramework client, String path)
         *  参数1：表示连接的客户端对象
         *  参数2：表示监听路径地址
         */
        TreeCache treeCache = new TreeCache(client,"/a");
        // 启动监听
        treeCache.start();
        // 添加监听
        treeCache.getListenable().addListener(new TreeCacheListener() {
            // 表示当根节点、子节点数据发生变化的时候，就会执行该方法
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                // 使用事件的类型，判断执行的是子节点的哪些变化
                if(event.getType() == TreeCacheEvent.Type.NODE_ADDED){
                    System.out.println("节点添加，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == TreeCacheEvent.Type.NODE_UPDATED){
                    System.out.println("节点修改，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == TreeCacheEvent.Type.NODE_REMOVED){
                    System.out.println("节点删除，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == TreeCacheEvent.Type.INITIALIZED){
                    System.out.println("初始化，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == TreeCacheEvent.Type.CONNECTION_SUSPENDED){
                    System.out.println("连接超时，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == TreeCacheEvent.Type.CONNECTION_RECONNECTED){
                    System.out.println("重试连接成功，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
                else if(event.getType() == TreeCacheEvent.Type.CONNECTION_LOST){
                    System.out.println("连接断开丢失，执行监听");
                    // 获取路径
                    String path = event.getData().getPath();
                    // 获取数据
                    byte[] dataByte = event.getData().getData();
                    String data = new String(dataByte);
                    System.out.println("当前路径是:"+path+"        数据是："+data);
                    System.out.println("---------------------------------------");
                }
            }
        });

        // 注意：当前junit测试的线程不能关闭，否则不能进行watch监听机制（后续的时候，web工程完成监听，只要web工程的服务器不停止工作，一直就会进行监听Zookeeper节点数据的变化）
        System.in.read();
        // 关闭客户端连接对象（注意：如果关闭，不能进行watch监听机制）
        // client.close();
    }


}
