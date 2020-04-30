package com.hong.dao;

import com.hong.domain.QueryVO;
import com.hong.domain.User;
import com.hong.domain.User2;

import java.util.List;

public interface UserDao {
    List<User> queryAll();

    int edit(User user);

    int delete(Integer id);

    int totalCount();

    /**
     * 根据用户名进行搜索。
     *  用户名放在了user对象里
     *  user对象放在了QueryVO对象里
     */
    List<User> searchByVo(QueryVO vo);

    List<User2> queryAllUser2();

    List<User> searchByUsername(String username);
}
