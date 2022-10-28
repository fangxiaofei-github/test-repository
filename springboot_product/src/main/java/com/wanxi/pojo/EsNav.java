package com.wanxi.pojo;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 导航栏信息
 * @author: fxf
 * @date: 2022/6/15 8:27
 */
@ApiModel("导航栏信息实体类")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(indexName = "es_nav", shards = 2, replicas = 1, createIndex = true)
public class EsNav implements Serializable {

    private static final long serialVersionUID = 1525269147827487913L;

    @Id
    private Integer id;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String navName;

    //@Field(type = FieldType.Keyword)
    private String navUrl;

    //@Field(type = FieldType.Integer)
    private Integer type; //导航栏类型：0表示头部导航栏信息   1表示新闻中心页面的导航栏信息  2表示产品导航栏

    //@Field(type = FieldType.Integer)
    private Integer status;

    //@Field(type = FieldType.Date)
    private Date createTime;

    //@Field(type = FieldType.Date)
    private Date updateTime;


    /**
     * 下面是查询条件时使用到的参数
     */
    //private String navName;
    //private String type;
    //private String status;
    //@Field(type = FieldType.Keyword)
    private String page; // 第几页数据
    //@Field(type = FieldType.Keyword)
    private String limit;  // 每页显示多少条数据


    // 起始行索引
    //@Field(type = FieldType.Integer)
    private Integer currentIndex;
    // 每页显示多少条数据
    //@Field(type = FieldType.Integer)
    private Integer currentCount;

}
