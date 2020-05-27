package io.seata.sample;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author jimin.jm@alibaba-inc.com
 * @date 2019/06/14
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AccountApplication {
//到时候连的是本地数据库,但是数据源是代理数据源决定
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    //就是将yml中的spring.datasource开头的属性的值赋值给返回的对象中的相同属性名的字段.
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")//等同于
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        //等同于 druidDataSource.setUrl(url)
        //等同于 druidDataSource.setUrl(url)
        //等同于 druidDataSource.setUrl(url)
        //等同于 druidDataSource.setUrl(url)
        return druidDataSource;
    }

    @Primary//用来表示 如果spring容器中有多个数据 就加载该数据源使用。 表示primary,就是说手动配置有了就不用自动配置了
    @Bean("dataSourceProxy")//代理数据源
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    /**
     *
     * 自动配置类：自动类中自动配置了数据源
     用的不是代理数据源，不要自配置，
     手动配置使用代理数据源
     * @param dataSourceProxy
     * @return
     */
    @Bean("jdbcTemplate")
    @ConditionalOnBean(DataSourceProxy.class)//spring容器中如果有该bean就进行注入
    public JdbcTemplate jdbcTemplate(DataSourceProxy dataSourceProxy) {
        return new JdbcTemplate(dataSourceProxy);
    }

}
