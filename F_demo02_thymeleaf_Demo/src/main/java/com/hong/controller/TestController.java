package com.hong.controller;

import com.hong.pojo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by zhangyuhong
 * Date:2020/5/13
 */
@RequestMapping("/test")
@Controller//不要用rest
public class TestController {
    @RequestMapping("/index")
    public String showIndex(Model model){
        //类似于request对象中设置key value
        model.addAttribute("message","哈哈哈哈我来自外星");
        model.addAttribute("message","message222");
        //获取POJO数据类型的数据设置
        model.addAttribute("person",new Person(1001L,"张三疯"));
        //设置集合列表数据返回
        List mylist = new ArrayList();
        mylist.add(new Person(1002L,"特朗普"));
        mylist.add(new Person(1003L,"fangfang"));
        mylist.add(new Person(1004L,"川建国"));
        model.addAttribute("list",mylist);

        Map<String,Object> mymap = new HashMap<>();
        for (Map.Entry<String, Object> entry : mymap.entrySet()) {

        }
        mymap.put("No","123");
        mymap.put("address","深圳");
        mymap.put("city","深圳");
        mymap.put("contry","zhognguo");
        mymap.put("identyt","tegong");
        //循环遍历输出map 集合
        model.addAttribute("map",mymap);


        model.addAttribute("canshu",1);
        //日期
        model.addAttribute("date",new Date());
        model.addAttribute("age",18);

        return "index";
    }
}
