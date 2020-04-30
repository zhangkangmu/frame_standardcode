package com.hong.ssm.dao;

import com.hong.ssm.domain.Items;

import java.util.List;

public interface ItemsDao {

    // 查询所有
    public List<Items> list();
    // 新增
    public int save(Items items);
}
