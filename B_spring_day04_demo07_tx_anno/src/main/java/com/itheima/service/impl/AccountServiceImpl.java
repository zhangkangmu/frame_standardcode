package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/18 14:53
 * @Version V1.0
 */

/**
 * @Transactional：可以放置到类上（类级别的事务，对所有方法有效），也可以放置到方法上（方法级别的事务），方法级别的事务覆盖类级别的事务
 * isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false：默认
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    public Account findByName(String name) {
        return accountDao.findByName(name);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
//    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = true)
//    类前面配置了
    public void transfer(String sourceName, String targetName, float money) {
//        try {
            // 1：使用sourceName查询aaa的账号，获取Account对象
            Account sourceAccount = accountDao.findByName(sourceName);
            // 2：使用targetName查询bbb的账号，获取Account对象
            Account targetAccount = accountDao.findByName(targetName);
            // 3：将aaa的账号 - money
            sourceAccount.setMoney(sourceAccount.getMoney()-money);
            // 4：将bbb的账号 + money
            targetAccount.setMoney(targetAccount.getMoney()+money);
            // 5：更新aaa的账号
            accountDao.update(sourceAccount);
            // 抛出一个异常
            int i = 10/0;
            // 6：更新bbb的账号
            accountDao.update(targetAccount);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("抛出运行时异常"+e.getMessage());
//        }
    }
}
