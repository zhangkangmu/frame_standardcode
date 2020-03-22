package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Account;
import com.itheima.ssm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName AccountController
 * @Description TODO
 * @Author ly
 * @Company 深圳黑马程序员
 * @Date 2020/3/22 9:31
 * @Version V1.0
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    // 查询所有
    @RequestMapping(value = "/findAll")
    public String findAll(Model model){
        System.out.println("访问AccountController类中的findAll方法！");
        List<Account> list = accountService.findAll();
        model.addAttribute("list",list);
        return "success"; // /WEB-INF/page/success.jsp
    }

    // 保存
    @RequestMapping(value = "/save")
    public String save(Account account){
        System.out.println("访问AccountController类中的save方法！");
        accountService.save(account);
        return "redirect:/account/findAll"; // 执行查询，然后在跳转到/WEB-INF/page/success.jsp
    }

}
