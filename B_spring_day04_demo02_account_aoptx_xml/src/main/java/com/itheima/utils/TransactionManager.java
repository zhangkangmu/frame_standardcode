package com.itheima.utils;

import java.sql.SQLException;

/**
 * @ClassName TransactionManager
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/16 10:23
 * @Version V1.0
 */
/**切面*/
public class TransactionManager {

    // 连接对象
    ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    // 开启事务（不设置事务自动提交）
    /**通知*/
    public void beginTransaction(){
        try {
            System.out.println("【前置通知】");
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 提交事务
    public void commit(){
        try {
            System.out.println("【后置通知】");
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 回滚事务
    public void rollback(){
        try {
            System.out.println("【异常通知】");
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭事务、解绑（线程和Connection对象完成解绑）
    public void close(){
        try {
            System.out.println("【最终通知】");
            connectionUtils.getThreadConnection().close();
            connectionUtils.releaseConnection(); // 解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
