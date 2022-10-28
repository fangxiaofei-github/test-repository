package com.wanxi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 用户信息实体类
 * @author: fxf
 * @date: 2022/7/26 19:04
 */
@ApiModel(value = "用户信息的实体类")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {


    private static final long serialVersionUID = -3265877735757306978L;

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String sex;
    private String tel;

    private Integer status;
    private Date createTime;
    private Date updateTime;


    /**
     * 用户对应的角色信息
     */
    List<Role> roleList = new ArrayList<>();
    // 角色id集合
    @ApiModelProperty(value = "角色id列表")
    List<Integer> roleIds = new ArrayList<>();

}
