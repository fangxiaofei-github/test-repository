package com.wanxi.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 角色信息
 * @author: fxf
 * @date: 2022/9/23 12:19
 */
@ApiModel("角色信息实体类")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Role implements Serializable {

    private static final long serialVersionUID = -9025927078605815670L;

    private Integer id;
    private String name;
    private String roleKey;
    private String status;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    /**
     * 是否删除（0未删除 1已删除）
     */
    private Integer delFlag;
    /**
     * 备注
     */
    private String remark;


    /**
     * 权限信息列表
     */
    @ApiModelProperty(value = "权限id列表")
    List<Integer> permsIds = new ArrayList<>();
}
