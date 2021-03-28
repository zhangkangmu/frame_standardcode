package com.hong.config;

import com.hong.domain.EnumSex;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhangyuhong
 * Date:2021/3/21
 */
public class MyEnumSexTypeHandler implements TypeHandler<EnumSex> {
	@Override
	public void setParameter(PreparedStatement ps, int i, EnumSex parameter, JdbcType jdbcType) throws SQLException {
		System.out.println("保存状态码!" + parameter.getCode());
		ps.setString(i, parameter.getCode().toString());
	}

	@Override
	public EnumSex getResult(ResultSet resultSet, String columnName) throws SQLException {
		int code = resultSet.getInt(columnName);
		System.out.println("columnName从数据库中获取的状态码" + code);
		if (code == 0) {
			return EnumSex.man;
		} else {
			return EnumSex.woman;
		}
	}

	@Override
	public EnumSex getResult(ResultSet rs, int columnIndex) throws SQLException {
		int code = rs.getInt(columnIndex);
		System.out.println("columnName--ResultSet从数据库中获取的状态码" + code);
		if (code == 0) {
			return EnumSex.man;
		} else {
			return EnumSex.woman;
		}
	}

	@Override
	public EnumSex getResult(CallableStatement cs, int columnIndex) throws SQLException {
		int code = cs.getInt(columnIndex);
		System.out.println("columnName--CallableStatement从数据库中获取的状态码" + code);
		if (code == 0) {
			return EnumSex.man;
		} else {
			return EnumSex.woman;
		}
	}
}
