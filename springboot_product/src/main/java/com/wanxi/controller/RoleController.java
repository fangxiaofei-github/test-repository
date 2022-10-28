package com.wanxi.controller;

import com.wanxi.pojo.Role;
import com.wanxi.service.RoleService;
import com.wanxi.vo.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 角色信息控制层
 * @author: fxf
 * @date: 2022/9/23 14:57
 */
@Api(tags = "角色信息控制层模块")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 新增角色信息
     * @param role
     * @return
     */
    @ApiOperation("新增角色信息接口")
    @RequestMapping("/addRole")
    public ResultInfo addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }


    /**
     * 删除角色信息
     * @return
     */
    @ApiOperation("删除角色信息接口")
    @RequestMapping("/delRole")
    public ResultInfo delRole(Integer roleId){
        return roleService.delRole(roleId);
    }

    /**
     * 修改角色信息
     * @return
     */
    @ApiOperation("修改角色信息接口")
    @RequestMapping("/updateRole")
    public ResultInfo updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    /**
     * 通过角色id查询角色信息
     */
    @ApiOperation("通过角色id查询角色信息接口")
    @RequestMapping("/selectRoleById")
    public ResultInfo selectRoleById(Integer roleId){
        return roleService.selectRoleById(roleId);
    }

}
