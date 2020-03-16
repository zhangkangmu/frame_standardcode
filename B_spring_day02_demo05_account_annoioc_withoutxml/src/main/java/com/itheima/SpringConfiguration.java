package com.itheima;

/**
 * @ClassName SpringConfiguration
 *
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/15 15:48
 * @Version V1.0
 */

import com.itheima.config.DataSourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;


/**
 * @Configuration 相当于applicationContext.xml
 * 因为它的出现，就可以取代applicationContext.xml，表示使用注解的配置
 * 注意：当使用AnnotationConfigApplicationContext进行加载类的时候，此时@Configuration的注解是可以省略的
 * @ComponentScan(value = {"com.itheima"})：表示组件的扫描，相当于取代<context:componentScan base-package=""/>
 * @Import(value = {DataSourceConfig.class})：表示导入其他的配置类，相当于取代<import></import>
 * @PropertySource(value = {"classpath:jdbc.properties"})，表示从类路径下加载jdbc.properties文件，相当于取<context:property-placeholder location="classpath:jdbc.properties>
 */
// @Configuration
@ComponentScan(value = {"com.itheima"})
@Import(value = {DataSourceConfig.class})
@PropertySource(value = {"classpath:jdbc.properties"})
public class SpringConfiguration {


}
