package com.hong.dao;

import com.hong.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 文章信息ES接口
 * @author Steven
 * @version 1.0
 * @description com.itheima.dao
 * @date 2020-4-30
 */
public interface ArticleDao extends ElasticsearchRepository<Article,Long> {

    /**
     * 根据content查询文章列表
     * @param content
     * @return
     */
    //命名查询-可以根据规则添加查询条件方法
    //findBy + 对象属性名 + (And Or Not) + 对象属性名
    List<Article> findByContent(String content);

    /**
     * 根据content分页查询文章列表
     * @param content
     * @return
     */
    //命名查询-可以根据规则添加查询条件方法
    //findBy + 对象属性名 + (And Or Not) + 对象属性名
    Page<Article> findByContent(String content, Pageable pageable);
}
