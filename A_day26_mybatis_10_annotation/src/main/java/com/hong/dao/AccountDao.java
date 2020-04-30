package com.hong.dao;

import com.hong.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author zhangyuhong
 * @date 2018/02/29
 */
public interface AccountDao {
    /**
     * 查询帐号信息，及其关联的用户信息。实现懒加载
     */
    @Select("select * from account")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            @Result(
                    property = "user",
                    column = "uid",
                    //配置：user 要调用另外一个功能，查询得到一个User对象
                    one = @One(
                            select = "com.itheima.dao.UserDao.findById",
                            //使用懒加载。不需要在核心配置文件里开启懒加载的开关，就可以实现懒加载功能
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<Account> queryAll();

    /**
     * 查询某一用户拥有的帐号集合
     * @param uid 用户的id
     * @return
     */
    @Select("select * from account where uid = #{uid}")
    List<Account> findByUid(Integer uid);
}
