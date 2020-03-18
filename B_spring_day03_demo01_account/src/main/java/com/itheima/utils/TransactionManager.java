package com.itheima.utils;

import java.sql.SQLException;

/**
 * @ClassName TransactionManager
 * @Description TODO
 * @Author ly
 * @Company
 * @Date 2020/3/16 10:23
 * @Version V1.0
 */
public class TransactionManager {

    // 连接对象
    ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    // 开启事务（不设置事务自动提交）
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 提交事务
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 回滚事务
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭事务、解绑（线程和Connection对象完成解绑）
    public void close(){
        try {
            connectionUtils.getThreadConnection().close();
            connectionUtils.releaseConnection(); // 解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
