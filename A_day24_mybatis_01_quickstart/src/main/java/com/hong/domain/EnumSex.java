package com.hong.domain;

/**
 * Created by zhangyuhong
 * Date:2021/3/21
 */
public enum EnumSex{
	man(0,"男"), woman(1,"女");

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

}
