package com.hong.统一异常处理类;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author by zhangyuhong
 * @date:2021/4/18
 */
@ControllerAdvice
public class AllException {


	//出错了跳转页面
	@ExceptionHandler(Exception.class)
	public String errorHtml(Exception e){
		System.out.println("张宇洪=="+e.getMessage());
		HashMap<String, Object> errorMap = new HashMap<>();
		errorMap.put("code", 500);
		errorMap.put("msg",  "出错了");
		return "forward:/error.html";
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String error(Exception e){
		System.out.println("张宇洪=="+e.getMessage());
		HashMap<String, Object> errorMap = new HashMap<>();
		errorMap.put("code", 500);
		errorMap.put("msg",  "出错了");
		return "一个返回数据的error格式类R.Error";
	}
}
