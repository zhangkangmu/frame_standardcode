package com.hong.接口限流;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangyuhong
 * Date:2020/6/18
 */

@Controller
@RequestMapping("/activity")
public class AopController {

    @ResponseBody
    @RequestMapping("/seckill")
    @AccessLimit(limit = 4,sec = 10)  //加上自定义注解即可
    public String test (HttpServletRequest request, @RequestParam(value = "username",required = false) String userName){
        //TODO somethings……
        return   "hello world !";
    }

}
