package com.itheima.service;

import com.itheima.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 文章信息业务逻辑接口
 * @author Steven
 * @description com.itheima.spring.es.service
 */
public interface ArticleService {
    /**
     * 保存文章信息
     * @param article 文章信息
     */
    void save(Article article);

    /*//删除文档
    public void delete(Article article);
    //批量导入数据
    public void save100(List<Article> articleList);
    //查询全部
    public Iterable<Article> findAll();
    //分页查询
    public Page<Article> findPage(Pageable pageable);*/
}
