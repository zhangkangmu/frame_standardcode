package com.hong.test;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhangyuhong
 * @version 1.0
 * @date 2020-4-29
 */
public class Demo01_ElastecsearchTest {

    private TransportClient client;

    //@Before，当前测试类中的所有@Test方法，在调用前都先调用前注解修饰的方法
    @Before
    public void init() throws UnknownHostException {
        /**
         * 1.创建客户端访问对象-TransportClient*
         *      Settings.builder().build():集群连接方式
         *      Settings.EMPTY：单机连接
         * */
        client = new PreBuiltTransportClient(Settings.EMPTY);
        //2.创建Elasticsearch连接对象-new InetSocketTransportAddress(InetAddress.getByName(地址), 端口号)
        TransportAddress address = new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300);
        //3.指定Elasticsearch连接地址与端口-client.addTransportAddress(address)
        client.addTransportAddress(address);
    }

    @Test
    public void testCreateIndex() {
        try {
            //TransportClient client = init();
            //4.准备索引-client.prepareIndex(索引名,类型名,文档id)

            //方式一：使用Map映射域
            /*IndexRequestBuilder indexRequestBuilder = client.prepareIndex("blog", "article", "1");
            //5.准备数据(域与映射)-indexRequestBuilder.setSource(map)
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", 1);
            map.put("title", "什么是Elasticsearch?");
            map.put("content", "ElasticSearch是一个基于Lucene的搜索服务器");
            indexRequestBuilder.setSource(map);*/

            //方式二：使用XContentBuilder映射域（推荐）
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex("blog", "article", "3");
            //构建XContentBuilder
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    //{id:2,title:"",content:""}
                    .startObject()  //对象开始 {
                        .field("id",3)
                        .field("title","3什么是Elasticsearch?")
                        .field("content","ElasticSearch是一个基于Lucene的搜索服务器3")
                    .endObject();  //对象结束 }
            indexRequestBuilder.setSource(builder);
            //6.执行保存索引与文档-indexRequestBuilder.get()
            IndexResponse response = indexRequestBuilder.get();
            //7.输出执行结果-response.sout()
            System.out.println(response);
            //8.关闭客户端-client.close()
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文档id查询文件信息(不走索引库)
     */
    @Test
    public void testGetById() {
        GetResponse response = client.prepareGet("blog", "article", "1").get();
        System.out.println("响应对象：" + response);
        System.out.println("文章对象：" + response.getSourceAsString());
        System.out.println("标题：" + response.getSource().get("title"));
    }

    /**
     * 查询所有文档(不走索引)
     */
    @Test
    public void testGetAll() {
        SearchResponse response = client.prepareSearch("blog").setTypes("article")
                //设置查询条件
                //QueryBuilders.matchAllQuery()：匹配所有
                .setQuery(QueryBuilders.matchAllQuery()).get();
        System.out.println("总记录数：" + response.getHits().getTotalHits());

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println("文章json:" + hit.getSourceAsString());
            System.out.println("标题:" + hit.getSource().get("title"));
            System.out.println("---------------------------");
        }
    }


    //+++++++++============================+++++++++++
    //以下方法 都走索引

    /**
     * 根据条件分词匹配查询
     * QueryBuilders.queryStringQuery("搜索条件").field(匹配的域名)
     * 会自动分词搜索条件，完成多个条件组合查询
     */
    @Test
    public void testGetByString() {
        SearchResponse response = client.prepareSearch("blog").setTypes("article")
                //设置查询条件
                //1.QueryBuilders.matchAllQuery()：匹配所有
                //2.QueryBuilders.queryStringQuery(""):自动分词匹配
                //      queryStringQuery("内容").field(匹配的域名)
                //.setQuery(QueryBuilders.queryStringQuery("Lucene搜").field("title")).get();
                .setQuery(QueryBuilders.queryStringQuery("elasticsearch搜").field("title")).get();
        System.out.println("总记录数：" + response.getHits().getTotalHits());

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println("文章json:" + hit.getSourceAsString());
            System.out.println("标题:" + hit.getSource().get("title"));
            System.out.println("---------------------------");
        }
    }


    /**
     * 词条查询-不分词
     * QueryBuilders.termQuery(域名, 搜索条件【不会分词】)
     */
    @Test
    public void testGetByTerm() {
        SearchResponse response = client.prepareSearch("blog").setTypes("article")
                //设置查询条件
                //1.QueryBuilders.matchAllQuery()：匹配所有

                //2.QueryBuilders.queryStringQuery(""):自动分词匹配
                //      queryStringQuery("title").field(匹配的域名)
                //.setQuery(QueryBuilders.queryStringQuery("Lucene搜").field("title")).get();
                //.setQuery(QueryBuilders.queryStringQuery("elasticsearch搜").field("title")).get();

                //3.词条查询QueryBuilders.termQuery(匹配的域名，匹配的内容)
                .setQuery(QueryBuilders.termQuery("title", "elasticsearch搜")).get();
        System.out.println("总记录数：" + response.getHits().getTotalHits());

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println("文章json:" + hit.getSourceAsString());
            System.out.println("标题:" + hit.getSource().get("title"));
            System.out.println("---------------------------");
        }
    }

    /**
     * 模糊查询(通配符查询)-不分词
     * QueryBuilders.wildcardQuery(域名,匹配的内容【可以使用通配符】)
     * *：0或者多个字符
     * ?：不多不少正好一个字符
     */
    @Test
    public void testWildcardQuery() {
        SearchResponse response = client.prepareSearch("blog").setTypes("article")
                //设置查询条件
                //1.QueryBuilders.matchAllQuery()：匹配所有

                //2.QueryBuilders.queryStringQuery(""):自动分词匹配
                //      queryStringQuery("title").field(匹配的域名)
                //.setQuery(QueryBuilders.queryStringQuery("Lucene搜").field("title")).get();
                //.setQuery(QueryBuilders.queryStringQuery("elasticsearch搜").field("title")).get();

                //3.词条查询QueryBuilders.termQuery(匹配的域名，匹配的内容)
                //.setQuery(QueryBuilders.termQuery("title", "elasticsearch搜")).get();

                //4.模糊查询(通配符查询)
                //QueryBuilders.wildcardQuery(域名,匹配的内容【可以使用通配符】)
                // *：0或者多个字符
                // ?：不多不少正好一个字符
                //.setQuery(QueryBuilders.wildcardQuery("title","elasticsearch")).get();
                //.setQuery(QueryBuilders.wildcardQuery("title","e?asticsearc?")).get();
                .setQuery(QueryBuilders.wildcardQuery("title","elasticse*")).get();
        System.out.println("总记录数：" + response.getHits().getTotalHits());

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println("文章json:" + hit.getSourceAsString());
            System.out.println("标题:" + hit.getSource().get("title"));
            System.out.println("---------------------------");
        }
    }
}
