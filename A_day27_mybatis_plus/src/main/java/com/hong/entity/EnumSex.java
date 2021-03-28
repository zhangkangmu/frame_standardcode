package com.hong.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * Created by zhangyuhong
 * Date:2021/3/21
 */
public enum EnumSex implements IEnum {
	man(0, "男"), woman(1, "女");

	@EnumValue
	private Integer code;


	private String name;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	EnumSex(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public Serializable getValue() {
		return this.code;
	}

//	man(0), woman(1);
//	private Integer code;
//
//	EnumSex(Integer code) {
//		this.code = code;
//	}
//
//	public Integer getCode() {
//		return code;
//	}
//
//	public void setCode(Integer code) {
//		this.code = code;
//	}

	//	man("男"), woman("女");
//	private String code;
//
//	EnumSex(String code) {
//		this.code = code;
//	}
}
