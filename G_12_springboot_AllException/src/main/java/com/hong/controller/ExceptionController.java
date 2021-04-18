package com.hong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date:2021/4/17
 * @author 洪
 */
@RestController
@RequestMapping("/config")
public class ExceptionController {

	@RequestMapping("/config/user/location")
	@ResponseBody
	public String testName() {
		int index=1/0;  //手动制作一个错误
		System.out.println("测试是否被全局处理类捕获到");
		return "ganmeOver";
	}
}
