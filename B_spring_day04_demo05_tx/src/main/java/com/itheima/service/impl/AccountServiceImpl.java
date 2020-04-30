package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @ClassName AccountServiceImpl
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/18 14:53
 * @Version V1.0
 */
public class AccountServiceImpl implements AccountService {

    AccountDao accountDao;

    TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

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
    public void transfer(String sourceName, String targetName, float money) {
        // 编程式事务控制第四步：使用TransactionTemplate
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            // doInTransactionWithoutResult方法中的业务，统一被spring的编程式事务控制
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                System.out.println("是否是新事务："+transactionStatus.isNewTransaction()); // true
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
            }
        });

    }
}
