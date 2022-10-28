package com.wanxi.pojo;

import io.swagger.annotations.ApiModel;
import lombok.*;

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
public class Nav {

    private Integer id;
    private String navName;
    private String navUrl;
    private Integer type; //导航栏类型：0表示头部导航栏信息   1表示新闻中心页面的导航栏信息  2表示产品导航栏

    private Integer status;
    private Date createTime;
    private Date updateTime;

}
