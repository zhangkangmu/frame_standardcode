package com.hong.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @ClassName DataSourceConfig
 @Company
 * @Date 2020/3/15 16:24
 * @Version V1.0
 */
public class DataSourceConfig {

    @Value(value = "${jdbc.driverClass}")
    private String driver;
    @Value(value = "${jdbc.jdbcUrl}")
    private String url;
    @Value(value = "${jdbc.user}")
    private String username;
    @Value(value = "${jdbc.password}")
    private String password;

    /**
     * @Bean：用来取代IOC和DI
     * @Bean：IOC（创建对象）
     *    * 通过方法的返回值，表示所创建的对象，相当于<bean id="createDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"/>
     *    * 改变bean的id值，使用@Bean(name = "dataSouce")，相当于<bean id="dataSouce" class="com.mchange.v2.c3p0.ComboPooledDataSource"/>
     */
    @Bean(name = "dataSouce")
    public DataSource createDataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
//            dataSource.setDriverClass("com.mysql.jdbc.Driver");
//            dataSource.setJdbcUrl("jdbc:mysql:///itcastspring");
//            dataSource.setUser("root");
//            dataSource.setPassword("root");
            dataSource.setDriverClass(driver);
            dataSource.setJdbcUrl(url); //jdbc:mysql:///itcastspring
            dataSource.setUser(username);
            dataSource.setPassword(password);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean(name = "dataSouce2")
    public DataSource createDataSource2(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql:///test");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * @Bean：IOC（创建对象）:使用方法的返回值完成创建对象
     *    * @Bean(name = "queryRunner")，相当于<bean id="queryRunner" class="org.apache.commons.dbutils.QueryRunner"/>
     * @Bean：DI（依赖注入）：使用方法的参数完成注入
     *    * DataSource dataSource放置到方法的参数上，相对于使用了@Autowired（先按照类型匹配，再按照名称匹配）
     */
    @Bean(name = "queryRunner")
    @Scope(value = "prototype")  //注意:这里是多例的
//    注解方式传递前面的bean值
//    这里是难点,注意Qualifier
    public QueryRunner createQueryRunner(@Qualifier(value = "dataSouce2") DataSource dataSource){
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return queryRunner;
    }
}
