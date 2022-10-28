package com.wanxi.pojo;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
 * @description: 留言对象
 * @author: fxf
 * @date: 2022/7/7 9:45
 */
@ApiModel("留言信息的实体类")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LeaveMsg {

    private Integer id;
    private String username;
    private String email;
    private String title;
    private String context;

    private Integer status;
    private Date createTime;
    private Date updateTime;

    //处理该条留言信息的用户ID
    private Integer userId;


}
