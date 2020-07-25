/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.controller;

import com.itheima.controller.result.ResultMsg;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Author: lvyang
 * @Created Date: 2019年07月29日
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@RestController
@RequestMapping("/account")
public class AccountDataController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResultMsg<List<Account>> findAll(HttpServletRequest request){
        ResultMsg<List<Account>> result = new ResultMsg();
        List<Account> list = accountService.findAll();
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultMsg<Boolean> add(HttpServletRequest request, Account account){
        ResultMsg<Boolean> result = new ResultMsg();
        if(accountService.add(account)){
            result.setMessage("添加成功！");
        }else{
            result.fail("添加失败！");
        }
        return result;
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public ResultMsg<Boolean> update(HttpServletRequest request, Account account){
        ResultMsg<Boolean> result = new ResultMsg();
        if(accountService.update(account)){
            result.setMessage("修改成功！");
        }else{
            result.fail("修改失败！");
        }
        return result;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResultMsg<Boolean> del(HttpServletRequest request,
                                  @PathVariable Integer id){
        ResultMsg<Boolean> result = new ResultMsg();
        if(accountService.delete(id)){
            result.setMessage("删除成功");
        }else{
            result.fail("删除失败！");
        }
        return result;
    }
}