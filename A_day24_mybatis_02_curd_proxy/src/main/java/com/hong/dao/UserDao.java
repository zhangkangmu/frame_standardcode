package com.hong.dao;

import com.hong.domain.User;

import java.util.List;

/**
 * - 保存用户（新增用户）
 * - 修改用户
 * - 删除用户
 * - 根据主键查询一个用户，得到`User`
 * - 查询数量
 * - 模糊查询：根据username进行模糊查询
 * @author zhangyuhong
 * @date 2018/02/27
 */
public interface UserDao {
    List<User> queryAll();

    int save(User user);

    int edit(User user);

    int delete(Integer id);

    User findById(Integer id);

    int totalCount();

    List<User> searchByUsername1(String username);

    List<User> searchByUsername2(String username);

    /**
     * 查询所有用户，根据指定的字段进行排序
     * @param orderColumn 要排序的字段名
     */
    List<User> searchByOrder(String orderColumn);
}
