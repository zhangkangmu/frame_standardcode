/**
 * Copyright (c) 2019 ucsmy.com, All rights reserved.
 */
package com.itheima.controller;

import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: lvyang
 * @Created 2019/12/20
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    /** 
    * @Description: findAll 
    * @Param: [request, response] 
    * @return: java.lang.String 
    * @Author: lvyang
    * @Date: 2019/12/20 
    */
    @RequestMapping("/list")
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "account/accountList";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request){
        request.setAttribute("title", "新增账户");
        return "account/add";
    }

    @RequestMapping("/update/{id}")
    public String updateById(HttpServletRequest request
        , @PathVariable Integer id
    ){
        Account account = accountService.selectById(id);
        request.setAttribute("account",account);
        request.setAttribute("title", "修改账户");
        return "account/add";
    }
}