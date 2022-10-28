package com.wanxi.pojo;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
 * @description: 公司信息
 * @author: fxf
 * @date: 2022/6/14 18:07
 */
@ApiModel("公司信息的实体类")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Company {

    private Integer id; //公司信息id
    private String companyName; //公司名称
    // 存储的是文件名称
    private String imgLogo;  //公司logo
    private String hotLineTel; //全国热线电话
    private String phone;   //公司电话 或者 招聘电话
    private String fax;     //传真
    private String address; //公司地址
    private String copyRight; //公司版权
    private String scanner;//公司描述

    /**
     * 添加联系方式属性
     */
    private String applyTel; //报名电话
    private String applyMail;  //报名邮箱
    private String applyAddress; //报名地址
    private String jobMail;//招聘邮箱

    private Integer status;
    private Date createTime;
    private Date updateTime;


}
