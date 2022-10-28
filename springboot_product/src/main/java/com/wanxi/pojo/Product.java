package com.wanxi.pojo;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 产品信息
 * @author: fxf
 * @date: 2022/6/15 10:20
 */
@ApiModel("产品信息实体类")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product implements Serializable {

    private static final long serialVersionUID = -2203228431754836516L;

    private Integer id;
    private String productImg;
    private String productName;
    private Double price;

    private Integer status;
    private Date createTime;
    private Date updateTime;

    //产品描述
    private String describe;

}
