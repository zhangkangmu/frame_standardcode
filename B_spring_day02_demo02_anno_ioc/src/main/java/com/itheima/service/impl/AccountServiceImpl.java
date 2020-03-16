package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName AccountServiceImpl
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/14 11:09
 * @Version V1.0
 */
// 组件
/**
 *  @Component：相当于在spring容器中定义：
 *    <bean id="accountServiceImpl" class="com.itheima.service.impl.AccountServiceImpl"></bean>
 *    此时的id表示类的名称，且首字母小写
 *  @Component("accountService")
 *    <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"></bean>
 */
// @Component("accountService")
@Service("accountService")
// @Scope(value = "prototype") // bean的作用域，控制所创建对象的单例和多例场景
public class AccountServiceImpl implements AccountService {

    /**
     * @Autowired（spring提供的）：放置到属性上，也可以放置到set方法上
     *    首先是按照类型注入，到spring容器中查找是否有对应的类型（bean class属性），如果类型存在，就直接完成注入
     *    如果类型不存在，回退到按照名称注入（bean id属性），如果名称存在，就完成注入(如果存在且是接口,但是实现类有多个,名称又不存在,就报错)
     *    如果名称不存在，就会抛出异常
     *
     * @Resource（jdk提供的）：放置到属性上，也可以放置到set方法上
     *    首先是按照名称注入，到spring容器中查找对应的名称（bean id属性），如果名称存在，就直接完成注入
     *    如果名称不存在，就回退到按照类型注入（bean class属性），如果类型也不存在，就会抛出异常
     *
     *    只想按照名称注入（bean id属性唯一）
     *    @Resource(name = "accountDao")：表示只能按照名称注入，使用name属性声明的名称accountDao到spring容器查找对应的名称完成注入
     *    *******************************************************************************************************
     *    @Autowired
          @Qualifier(value = "accountDao")：表示只能按照名称注入，使用value属性声明的名称accountDao到spring容器查找对应的名称完成注入
     */
    // 创建Dao（注入）
    @Autowired
    @Qualifier(value = "accountDao")  //注意:一定要指定值
    // @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Value(value = "张三")
    private String name;
    @Value(value = "20")
    private Integer age;

    //@Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }




    public void saveAccount() {
        System.out.println("执行AccountServiceImpl类中的saveAccount方法   name="+name+"   age="+age);
        accountDao.save();
    }


    // 初始化
    @PostConstruct // 相当于<bean init-method="init">
    public void init(){
        System.out.println("初始化的方法...");
    }
    // 销毁
    @PreDestroy // 相当于<bean destory-method="destroy">
    public void destroy(){
        System.out.println("销毁的方法...");
    }
}
