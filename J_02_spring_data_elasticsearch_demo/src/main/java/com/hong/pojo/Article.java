package com.hong.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 文章信息业务实体
 * @Document 标识为一个ES业务实体(indexName:索引库名称，type:类型名称,shards:分片的数量,replicas:复制的数量)
 * @Id 绑定属性为文档id,一般情况下，我们使用数据库查询到的主键作为ES的文档id
 * @Field 绑定普通域的内容{
 *     type:域的数据类型
 *     index:是否创建索引(为了后续查询)，默认值是 true
 *     store:是否存储一件原始到lucene，默认值false
 *     analyzer:指定创建索引时使用的分词器(默认standard)
 *     searchAnalyzer:指定搜索数据时使用的分词器，默认值与analyzer属性一样，可以忽略不设置
 * }
 */
@Document(indexName = "blog3",type = "article")
public class Article {
    @Id
    private Long id;
    @Field(type = FieldType.Text,index = true,store = false,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String title;
    @Field(type = FieldType.Text,index = true,store = false,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String content;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
