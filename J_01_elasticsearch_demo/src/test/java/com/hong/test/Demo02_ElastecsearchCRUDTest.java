package com.hong.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.pojo.Article;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author Steven
 * @version 1.0
 * @description com.itheima.test
 * @date 2020-4-29
 */
public class Demo02_ElastecsearchCRUDTest {

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
    public void testCreateAndDeleteIndex() {
        try {
            //通过Admin创建索引库
            //client.admin().indices().prepareCreate("blog2").get();

            //通过Admin删除索引库
            client.admin().indices().prepareDelete("blog2").get();
            //8.关闭客户端-client.close()
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建映射
    @Test
    public void testCreatdMapping() {
        try {
            //先创建索引库
            client.admin().indices().prepareCreate("blog2").get();
            //优化方案：SpringDataElasticsearch
            //映射使用XcontentBuilder
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()   //{
                        .startObject("article")  //article:{
                            .startObject("properties")
                                .startObject("id")
                                    .field("type","long")  //域的类型
                                    .field("store",false)  //是否存储(lucene)，后续的ES默认设置为false
                                    .field("index",true)  //是否索引（创建索引）
                                .endObject()
                                .startObject("title")
                                    .field("type","text")  //域的类型
                                    .field("store",false)  //是否存储(lucene)，后续的ES默认设置为false
                                    .field("index",true)  //是否索引（创建索引）
                                    //使用的分词器：standard(默认) ik_smart(标准ik切分) ik_max_word（ik最细切分）
                                    .field("analyzer","ik_smart")
                                .endObject()
                                .startObject("content")
                                    .field("type","text")  //域的类型
                                    .field("store",false)  //是否存储(lucene)，后续的ES默认设置为false
                                    .field("index",true)  //是否索引（创建索引）
                                    //使用的分词器：standard(默认) ik_smart(标准ik切分) ik_max_word（ik最细切分）
                                    .field("analyzer","ik_smart")
                                .endObject()
                            .endObject()
                        .endObject() //}
                    .endObject();  //}
            //把映射设置到索引库上
            PutMappingRequest request = Requests.putMappingRequest("blog2").type("article").source(builder);
            //创建映射
            client.admin().indices().putMapping(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建文档-ContentBuilder
    @Test
    public void testCreateDoc() {
        try {
            //方式一(回顾)：使用XContentBuilder映射域（推荐）
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex("blog2", "article", "1");
            //构建XContentBuilder
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    //{id:2,title:"",content:""}
                    .startObject()  //对象开始 {
                        .field("id",1)
                        .field("title","什么是Elasticsearch?")
                        .field("content","ElasticSearch是一个基于Lucene的搜索服务器")
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
     * 回顾：jackJson
     * 使用ObjectMapper进行转换时，要求：json串的属性名一定要与java对象属性名一致
     */
    @Test
    public void testJackJsonMapper() {
        try {
            Article article = new Article();
            article.setId(2L);
            article.setTitle("2什么是Elasticsearch");
            article.setContent("2ElasticSearch是一个基于Lucene的搜索服务器");

            //创建ObjectMapper
            //使用ObjectMapper进行转换时，要求：json串的属性名一定要与java对象属性名一致
            ObjectMapper mapper = new ObjectMapper();
            //把对象转成json串
            String json = mapper.writeValueAsString(article);
            System.out.println(json);

            String myJson = "{\"id\":2,\"title\":\"2什么是Elasticsearch\",\"content\":\"2ElasticSearch是一个基于Lucene的搜索服务器\"}";
            //把json串转换成对象
            Article article1 = mapper.readValue(myJson, Article.class);
            System.out.println(article1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建文档-根据对象
    //先把对象转换成JSON串才可以保存
    @Test
    public void testCreateDocByArticle() {
        try {
            //方式一(回顾)：使用XContentBuilder映射域（推荐）
            IndexRequestBuilder indexRequestBuilder = client.prepareIndex("blog2", "article", "2");

            Article article = new Article();
            article.setId(2L);
            article.setTitle("22什么是Elasticsearch");
            article.setContent("2ElasticSearch是一个基于Lucene的搜索服务器");

            //创建ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            //设置数据-setSource(对象json串,类型)
            indexRequestBuilder.setSource(mapper.writeValueAsString(article), XContentType.JSON);
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
     * ES修改文档操作：
     * 1.4.3.1. 方案一：使用prepareIndex，刚才上面的保存文档方式，同样可以更新(推荐)
     * 1.4.3.2. 方案二：使用prepareUpdate修改文档
     * 1.4.3.3. 方案三：直接使用update()修改文档
     */
    @Test
    public void testUpdateDoc() {
        try {

            Article article = new Article();
            article.setId(2L);
            article.setTitle("66什么是Elasticsearch");
            article.setContent("2ElasticSearch是一个基于Lucene的搜索服务器");
            //创建ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            //1.4.3.1. 方案一：使用prepareIndex，刚才上面的保存文档方式，同样可以更新(推荐)
            /*IndexResponse response = client.prepareIndex("blog2", "article", "2")
                    .setSource(mapper.writeValueAsString(article), XContentType.JSON).get();*/

            //1.4.3.2. 方案二：使用prepareUpdate修改文档
            /*
            client.prepareUpdate("blog2", "article", "2")
                    .setDoc(mapper.writeValueAsString(article), XContentType.JSON).get();*/

            //1.4.3.3. 方案三：直接使用update()修改文档
            ActionFuture<UpdateResponse> response = client.update(new UpdateRequest("blog2", "article", "2")
                    .doc(mapper.writeValueAsString(article), XContentType.JSON));
            System.out.println(response.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ES删除文档操作
     * 方案一：通过prepareDelete 删除文档
     */
    @Test
    public void testDeleteDoc() throws Exception{
        //方案一：通过prepareDelete 删除文档
        //DeleteResponse response = client.prepareDelete("blog2", "article", "2").get();
        //System.out.println(response);

        //方案二：直接使用delete() 删除文档
        ActionFuture<DeleteResponse> delete = client.delete(new DeleteRequest("blog2", "article", "1"));
        System.out.println(delete.get());
    }

    /**
     * 批量导入100条数据
     */
    @Test
    public void testBulkCreate() {
        try {
            //创建批量导入构建器
            BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
            Article article = null;
            ObjectMapper mapper = new ObjectMapper();
            //准备100条数据
            for (long i = 0; i < 100; i++) {
                article = new Article();
                article.setId(i);
                article.setTitle(i + "什么是Elasticsearch");
                article.setContent("ElasticSearch是一个基于Lucene的搜索服务器" + i);
                //把对象放入构建器
                bulkRequestBuilder.add(client.prepareIndex("blog2", "article", i + "")
                        .setSource(mapper.writeValueAsString(article), XContentType.JSON));
                /*bulkRequestBuilder.add(new IndexRequest("blog2", "article", i + "")
                        .source(mapper.writeValueAsString(article), XContentType.JSON));*/
            }
            //执行批量导入
            BulkResponse bulkResponse = bulkRequestBuilder.get();
            //查看执行结果
            System.out.println(bulkResponse.status());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询文档
     * 1.匹配所有：QueryBuilders.matchAllQuery()
     * 2.字符串查询：QueryBuilders.queryStringQuery("搜索条件").field(匹配的域名)
     * 3.词条查询：QueryBuilders.termQuery(匹配的域名，匹配的内容)
     * 4.模糊查询(通配符查询)：QueryBuilders.wildcardQuery(域名,匹配的内容【可以使用通配符】)
     * 5.相似度查询(失真查询)：QueryBuilders.fuzzyQuery(域名, 查询条件)，容错两个字母以内
     * 6.范围匹配查询(数值、时间)-QueryBuilders.rangeQuery(域名).from(开始).to(结束)
     * 7.组合查询(多条件匹配)-QueryBuilders.boolQuery().组合方式(must、mustNot、should)
     */
    @Test
    public void testQuery() {
        SearchResponse response = client.prepareSearch("blog2").setTypes("article")
                //设置查询条件
                //1.QueryBuilders.matchAllQuery()：匹配所有

                //2.QueryBuilders.queryStringQuery(""):自动分词匹配
                //      queryStringQuery("title").field(匹配的域名)
                //.setQuery(QueryBuilders.queryStringQuery("Lucene搜").field("title")).get();
                //.setQuery(QueryBuilders.queryStringQuery("ElasticSearch").field("title")).get();

                //3.词条查询QueryBuilders.termQuery(匹配的域名，匹配的内容)
                //.setQuery(QueryBuilders.termQuery("title", "elasticsearch")).get();

                //4.模糊查询(通配符查询)
                //QueryBuilders.wildcardQuery(域名,匹配的内容【可以使用通配符】)
                // *：0或者多个字符
                // ?：不多不少正好一个字符
                //.setQuery(QueryBuilders.wildcardQuery("title","elasticsearch")).get();
                //.setQuery(QueryBuilders.wildcardQuery("title","e?asticsearc?")).get();
                //.setQuery(QueryBuilders.wildcardQuery("title","*8")).get();

                //5.相似度查询(失真查询)：QueryBuilders.fuzzyQuery(域名, 查询条件)，容错两个字母以内
                //.setQuery(QueryBuilders.fuzzyQuery("title", "elasticseaaah")).get();
                //中文一个字占两个位
                //.setQuery(QueryBuilders.fuzzyQuery("content", "服务哥")).get();

                //6.范围匹配查询(数值、时间)-QueryBuilders.rangeQuery(域名).from(开始).to(结束)
                //.setQuery(QueryBuilders.rangeQuery("id").from(0).to(5)).get();

                //7.组合查询-QueryBuilders.boolQuery().组合方式(must、mustNot、should)
                //must：必须同时成立 AND
                //mustNot：必须同时不成立 NOT
                //should：或者的意思 OR
                .setQuery(QueryBuilders.boolQuery()
                        //追加第一个条件
                        .must(QueryBuilders.queryStringQuery("elasticsearch搜").field("title"))
                        //追加第二个条件
                        .mustNot(QueryBuilders.rangeQuery("id").from(0).to(5))
                ).get();
        System.out.println("总记录数：" + response.getHits().getTotalHits());

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println("文章json:" + hit.getSourceAsString());
            System.out.println("标题:" + hit.getSource().get("title"));
            System.out.println("---------------------------");
        }
    }



    /**
     * 分页查询
     * setFrom(起始行号)-类似于mysql limmit(0,5) 中的0
     * setSize(每页查询的条数)
     * addSort(域名, 排序方式【SortOrder.ASC，SortOrder.DESC】)
     */
    @Test
    public void testQueryByPage() {
        SearchResponse response = client.prepareSearch("blog2").setTypes("article")
                //7.组合查询-QueryBuilders.boolQuery().组合方式(must、mustNot、should)
                //must：必须同时成立 AND
                //mustNot：必须同时不成立 NOT
                //should：或者的意思 OR
                .setQuery(QueryBuilders.boolQuery()
                                //追加第一个条件
                                .must(QueryBuilders.termQuery("title", "ElasticSearch".toLowerCase()))
                                //追加第二个条件
                                //.mustNot(QueryBuilders.rangeQuery("id").from(0).to(5))
                )
                //分页条件
                .setFrom(0) //setFrom(起始行号)-类似于mysql limmit(0,5) 中的0
                .setSize(5)  //setSize(每页查询的条数)
                //设置排序-addSort(域名, 排序方式【SortOrder.ASC，SortOrder.DESC】)
                .addSort("id", SortOrder.ASC)
                //执行查询
                .get();
        System.out.println("总记录数：" + response.getHits().getTotalHits());

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println("文章json:" + hit.getSourceAsString());
            System.out.println("标题:" + hit.getSource().get("title"));
            System.out.println("---------------------------");
        }
    }


    /**
     * 高亮查询
     * 1.准备高亮条件
     * 2.设置高亮条件
     * 3.提取高亮数据
     */
    @Test
    public void testHighlight() throws Exception{
        //1、准备高亮数据
        HighlightBuilder hBuilder = new HighlightBuilder();
        //添加前缀
        hBuilder.preTags("<em style='color:red;'>");
        //添加后缀
        hBuilder.postTags("</em>");
        //设置设置高亮的域名
        hBuilder.field("title");
        //hBuilder.field("content");
        //hBuilder.fragmentSize(3);

        SearchResponse response = client.prepareSearch("blog2").setTypes("article")
                //7.组合查询-QueryBuilders.boolQuery().组合方式(must、mustNot、should)
                //must：必须同时成立 AND
                //mustNot：必须同时不成立 NOT
                //should：或者的意思 OR
                .setQuery(QueryBuilders.boolQuery()
                                //追加第一个条件
                                .must(QueryBuilders.termQuery("title", "ElasticSearch".toLowerCase()))
                                //.must(QueryBuilders.termQuery("content", "ElasticSearch".toLowerCase()))
                        //追加第二个条件
                        //.mustNot(QueryBuilders.rangeQuery("id").from(0).to(5))
                )
                //分页条件
                .setFrom(0) //setFrom(起始行号)-类似于mysql limmit(0,5) 中的0
                .setSize(5)  //setSize(每页查询的条数)
                //设置排序-addSort(域名, 排序方式【SortOrder.ASC，SortOrder.DESC】)
                .addSort("id", SortOrder.ASC)

                //2.设置高亮条件
                .highlighter(hBuilder)
                //执行查询
                .get();
        System.out.println("总记录数：" + response.getHits().getTotalHits());

        ObjectMapper mapper = new ObjectMapper();
        for (SearchHit hit : response.getHits().getHits()) {
            //把json串，转换为Article
            Article article = mapper.readValue(hit.getSourceAsString(), Article.class);
            System.out.println("高亮前：" + article);
            //3.提取高亮数据，替换无高亮属性
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields != null) {
                //提取某个域的高亮数据,注意，此时高亮数据，获取结果跟查询条件与设置高亮域名有关系
                HighlightField titleHighlight = highlightFields.get("title");
                //HighlightField titleHighlight = highlightFields.get("content");
                String title = "";
                //提取碎片
                for (Text fragment : titleHighlight.getFragments()) {
                    title += fragment;
                }
                //把无高亮的title换成高亮数据
                article.setTitle(title);
                //article.setContent(title);
            }
            System.out.println("高亮后：" + article);
            System.out.println("---------------------------");
        }
    }
}
