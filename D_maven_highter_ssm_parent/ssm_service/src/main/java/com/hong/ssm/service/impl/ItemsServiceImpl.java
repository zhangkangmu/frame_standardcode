package com.hong.ssm.service.impl;

import com.hong.ssm.dao.ItemsDao;
import com.hong.ssm.domain.Items;
import com.hong.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ItemsServiceImpl
 * @Description TODO
 * @Author ly
 *
 * @Date 2020/3/23 11:25
 * @Version V1.0
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    ItemsDao itemsDao;

    @Override
    public List<Items> list() {
        List<Items> list = itemsDao.list();
        return list;
    }

    @Override
    public int save(Items items) {
        int rows = itemsDao.save(items);
        return rows;
    }
}
