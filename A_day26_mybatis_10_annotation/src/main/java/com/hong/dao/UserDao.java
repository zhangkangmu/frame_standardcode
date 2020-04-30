package com.hong.dao;

import com.hong.domain.User;
import com.hong.domain.User2;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 实际开发中：通常是注解和xml配置混用。
 *      简单功能、几乎不变的功能，使用注解
 *      复杂功能、可能会变的功能，使用xml
 *
 *      注意：一个功能，一定不要重复配置
 *
 * Mybatis里什么样的功能适合使用注解：
 *      1. 简单的CURD功能，比如最基础增、删、改、查
 *      2. 几乎不变的功能
 *
 * 动态SQL拼接：注解也可以实现，但是非常麻烦。建议仍然使用xml方式配置
 *
 * 单表的简单CURD，使用注解实现：
 *      查询全部
 *      根据主键查询一个
 *      添加
 *      修改
 *      删除
 *      查询数量
 *      模糊查询
 *
 *      JavaBean属性名和字段名不一致的情况
 *
 * @author zhangyuhong
 * @date 2018/02/29
 */
public interface UserDao {

    @Select("select * from user")
    List<User> queryAll();

    /**
     * 如果方法只有一个参数，并且是简单类型，那么#{}里边随意写
     */
    @Select("select * from user where id = #{uid}")
    User findById(Integer id);

    @Insert("insert into user(id,username,birthday,sex,address)values (#{id},#{username},#{birthday},#{sex},#{address})")
    @SelectKey(statement = "select last_insert_id()", resultType = Integer.class, keyProperty = "id", before = false)
    void save(User user);

    @Update("update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address} where id=#{id}")
    void edit(User user);

    @Delete("delete from user where id = #{id}")
    void delete(Integer id);

    @Select("select count(*) from user")
    int totalCount();

    @Select("select * from user where username like #{username}")
    List<User> searchByUsername(String username);

    @Select("select * from user where username like '%${value}%'")
    List<User> searchByUsername2(String username);

    @Select("select * from user")
    @Results({//相当于resultMap标签
            @Result(property = "userId",column = "id", id = true), //相当于resultMap里的result/id标签
            @Result(property = "username",column = "username"),
            @Result(property = "userBirthday",column = "birthday"),
            @Result(property = "userSex",column = "sex"),
            @Result(property = "userAddress",column = "address")
    })
    List<User2> queryAllUser2();

    /**
     * 查询用户信息，及其关联的帐号集合信息。使用懒加载
     */
    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "username",column = "username"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "address",column = "address"),
            @Result(
                    property = "accounts",
                    column = "id",
                    many = @Many(
                            //调用一个功能：查询某一用户拥有的帐号集合
                            select = "com.itheima.dao.AccountDao.findByUid",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<User> queryAllUserWithAccounts();
}
