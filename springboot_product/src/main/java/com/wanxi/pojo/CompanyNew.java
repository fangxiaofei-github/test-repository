package com.wanxi.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
 * @description: 公司新闻信息
 * @author: fxf
 * @date: 2022/6/15 9:52
 */
@ApiModel("公司新闻信息的实体类")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyNew {

    private Integer id; //id
    private String title;
    private String context;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date newTime;

    /**
     * 0   网站首页----公司新闻
     * 1   网站首页----媒体报道
     * 2   新闻中心----新闻信息
     */
    private Integer type; //新闻类型

    private Integer status;
    private Date createTime;
    private Date updateTime;


}
