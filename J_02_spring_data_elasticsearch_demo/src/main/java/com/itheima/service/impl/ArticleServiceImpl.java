package com.itheima.service.impl;

import com.itheima.dao.ArticleDao;
import com.itheima.pojo.Article;
import com.itheima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.service.impl
 * @date 2020-4-30
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public void save(Article article) {
        articleDao.save(article);
    }
}
