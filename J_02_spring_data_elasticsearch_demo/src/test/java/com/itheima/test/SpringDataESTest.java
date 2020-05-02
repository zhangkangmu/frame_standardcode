package com.itheima.test;

import com.itheima.dao.ArticleDao;
import com.itheima.pojo.Article;
import com.itheima.service.ArticleService;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.test
 * @date 2020-4-30
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataESTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ArticleDao articleDao;

    //创建索引库
    @Test
    public void testCreateIndexAndMapping() {
        //创建索引库
        elasticsearchTemplate.createIndex(Article.class);
        //创建映射,可以省略
        //elasticsearchTemplate.putMapping(Article.class);
    }

    @Test
    public void testCreateDoc() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("什么是Elasticsearch");
        article.setContent("ElasticSearch是一个基于Lucene的搜索服务器");

        //保存Article到索引库
        articleService.save(article);
    }

    @Test
    public void testDelete() {
        Article where = new Article();
        where.setId(1L);
        articleDao.delete(where);
    }

    @Test
    public void testSave100() {
        List<Article> articleList = new ArrayList<Article>();
        for (long i = 0; i < 100; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle(i + "什么是Elasticsearch");
            article.setContent("ElasticSearch是一个基于Lucene的搜索服务器" + i);
            articleList.add(article);
        }
        //批量导入
        articleDao.saveAll(articleList);
    }

    @Test
    public void testFindAll() {
        Iterable<Article> all = articleDao.findAll();
        for (Article article : all) {
            System.out.println(article);
        }
    }

    @Test
    public void testFindPage() {
        //PageRequest.of(当前页【从0开始】，每页查询条数,排序对象)
        Pageable pageable = PageRequest.of(1,5,new Sort(Sort.Direction.ASC,"id"));
        Page<Article> page = articleDao.findAll(pageable);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        for (Article article : page) {
            System.out.println(article);
        }
    }

    @Test
    public void testQueryByMothodName() {
        //支持通配符
        List<Article> articleList = articleDao.findByContent("*8");
        for (Article article : articleList) {
            System.out.println(article);
        }

        System.out.println("----------------------------------------------");
        Page<Article> page = articleDao.findByContent(
                "*8",
                PageRequest.of(0, 5, new Sort(Sort.Direction.ASC, "id"))
        );
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        for (Article article : page) {
            System.out.println(article);
        }
    }
}
