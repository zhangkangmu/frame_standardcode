package com.itheima.main;

import java.sql.*;

/**
 * @ClassName TestJdbc
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/14 10:41
 * @Version V1.0
 */
public class TestJdbc {

    public static void main(String[] args) throws Exception {
        //1.注册驱动
        // DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // 对象的耦合
        // 解决方案（通过反射，解决对象之间的耦合）（这是spring底层的核心思想）
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接（Connection）
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/itcastspring","root","root");
        //3.获取操作数据库的预处理对象（PreparedStatement）
        PreparedStatement ps = connection.prepareStatement("select * from account");
        //4.执行SQL，得到结果集（ResultSet）
        ResultSet rs = ps.executeQuery();
        //5.遍历结果集
        while (rs.next()){
            String name = rs.getString("name");
            System.out.println("name:"+name);
        }
        //6.释放资源
        rs.close();
        ps.close();
        connection.close();
    }
}
