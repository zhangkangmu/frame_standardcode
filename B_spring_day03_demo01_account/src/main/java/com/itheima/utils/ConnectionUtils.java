package com.itheima.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName ConnectionUtils
 * @Description TODO
 * @Author ly
 * @Company
 * @Date 2020/3/16 10:15
 * @Version V1.0
 */
// 连接对象，使用本地线程获取连接对象，将连接Connection和本地线程绑定
public class ConnectionUtils {
    // 线程连接对象
    ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    // 数据源
    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 获取连接，并让当前连接和本地线程绑定
    public Connection getThreadConnection(){
        Connection connection = tl.get();
        if(connection == null){
            try {
                connection = dataSource.getConnection();
                tl.set(connection);// 线程和Connection绑定
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // 将Connection对象和本地线程解绑
    public void releaseConnection(){
        tl.remove();
    }
}
