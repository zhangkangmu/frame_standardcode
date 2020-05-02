package com.hong.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hong.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyuhong
 * Date:2020/4/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testInset() {
        User user = new User();
        user.setAge(18);
        user.setEmail("1222211789@qq.com");
        user.setName("yu");
        userMapper.insert(user);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1L);
        user.setAge(20);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    /**
     * 测试 乐观锁插件
     */
    @Test
    public void testOptimisticLocker() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
        //修改数据
        user.setName("Helen Yao");
        user.setEmail("helen@qq.com");
        //执行更新
        userMapper.updateById(user);
    }

    /**
     * 测试乐观锁插件 失败
     */
    @Test
    public void testOptimisticLockerFail() {
        //查询
        User user = userMapper.selectById(1L);
        //修改数据
        user.setName("Helen Yao1");
        user.setEmail("helen1@qq.com");
        //模拟另一个线程中间更新了数据
        //查询
        User user2 = userMapper.selectById(1L);
        //修改数据
        user2.setName("Helen Yao2");
        user2.setEmail("helen2@qq.com");
        userMapper.updateById(user2);
        //执行更新
        userMapper.updateById(user);
    }

    @Test
    public void testSelectById() {

        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    @Test
    public void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 11);
        map.put("name", "Helen Yao");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //测试selectPage分页
    @Test
    public void testSelectPage() {
        //当前页,每页多少数量
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);
        List<User> records = page.getRecords();
        //System.out::println表示方法引用
        records.forEach(System.out::println);
        System.out.println("当前页码:" + page.getCurrent());
        System.out.println("总页数:" + page.getPages());
        System.out.println("每页记录数:" + page.getSize());
        System.out.println("总记录数:" + page.getTotal());
        System.out.println("是否有下一页:" + page.hasNext());
        System.out.println("是否有上一页:" + page.hasPrevious());
    }

    //测试selectMapsPage分页：结果集是Map
    @Test
    public void testSelectMapsPage() {
        Page<User> page = new Page<>(1, 5);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
        //注意：此行必须使用 mapIPage 获取记录列表，否则会有数据类型转换错误
        mapIPage.getRecords().forEach(System.out::println);
        System.out.println("当前页码:" + page.getCurrent());
        System.out.println("总页数:" + page.getPages());
        System.out.println("每页记录数:" + page.getSize());
        System.out.println("总记录数:" + page.getTotal());
        System.out.println("是否有下一页:" + page.hasNext());
        System.out.println("是否有上一页:" + page.hasPrevious());
    }

    //    1、根据id删除记录
    @Test
    public void testDeleteById() {
        userMapper.deleteById(1256284808141615105L);
    }

    //    2、批量删除
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(result);
    }

    //    3、简单的条件查询删除
    @Test
    public void testDeleteByMap() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);

        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }
    //4.逻辑删除
    @Test
    public void testLogicDelete() {
        int i = userMapper.deleteById(1256450481392193538L);  //会自动将deleted=1
        System.out.println(i);

        //前面删除了,虽然还在数据库中,但是实际上已经找不到了,因为里面实际上包含了一个 AND deleted=0
       //User user = userMapper.selectById(1256450481392193538L);
       //System.out.println(user);
    }
}