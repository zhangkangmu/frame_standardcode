package com.hong.mybatis.sqlsession.defaults;

import com.hong.mybatis.cfg.Configuration;
import com.hong.mybatis.sqlsession.SqlSession;
import com.hong.mybatis.sqlsession.proxy.MapperProxy;
import com.hong.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author
 * SqlSession接口的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));
    }

    /**
     * 用于释放资源
     */
    @Override
    public void close() {
            if(connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
            }
        }
    }
}
