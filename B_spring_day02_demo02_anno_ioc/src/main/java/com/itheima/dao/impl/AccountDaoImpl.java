package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 * @ClassName AccountDaoImpl
 * @Author ly
 * @Date 2020/3/15 10:35
 * @Version V1.0
 */
// 组件
/**
 *  @Component：相当于在spring容器中定义：
 *    <bean id="accountDaoImpl" class="com.itheima.dao.impl.AccountDaoImpl"></bean>
 *    此时的id表示类的名称，且首字母小写
 *  @Component("accountDao")
 *    <bean id="accountDao" class="com.itheima.dao.impl.AccountDaoImpl"></bean>
 *  @Controller、@Service、@Repository称之为@Component的衍生组件
 *    @Controller：表现层组件，SpringMVC场景
 *    @Service：业务层组件
 *    @Repository：持久层组件，Mybatis、SpringDataJPA...
 */
//@Component("accountDao")
@Repository("accountDao") // 该类是一个Dao
public class AccountDaoImpl implements AccountDao {
    public void save() {
        System.out.println("执行AccountDaoImpl类中的save方法！");
    }
}
