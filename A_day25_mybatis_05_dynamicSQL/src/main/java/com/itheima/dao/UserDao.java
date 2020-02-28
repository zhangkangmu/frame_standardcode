package com.itheima.dao;

import com.itheima.domain.QueryVO;
import com.itheima.domain.User;

import java.util.List;

/**
 * @author liuyp
 * @date 2020/02/28
 */
public interface UserDao {

    /**
     * 根据用户名和性别搜索用户
     * @param user 搜索条件对象。其中有username和sex
     */
    List<User> search(User user);

    /**
     * 根据id数组，查询对应的用户
     * @param vo 查询条件对象。其中有Integer[] ids
     * @return
     */
    List<User> searchForeach(QueryVO vo);

    /**
     * 根据QueryVO里的条件进行搜索。
     *  ids：id数组，可能有值，也可能没有
     *  username：用户名，可能为空
     *  sex：性别，可能为空
     *
     * @param vo 搜索条件。其中有Integer[] ids, String username, String sex
     */
    List<User> search2(QueryVO vo);
}
