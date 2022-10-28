package com.wanxi.controller;

import com.wanxi.pojo.Role;
import com.wanxi.pojo.User;
import com.wanxi.service.RoleMenuService;
import com.wanxi.vo.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 角色权限信息管理
 * @author: fxf
 * @date: 2022/9/23 15:36
 */
@Api(tags = "角色权限信息管理控制层模块")
@RestController
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 给角色添加权限
     */
    @ApiOperation("给角色添加权限信息")
    @RequestMapping("/addRoleMenu")
    public ResultInfo addRoleMenu(@RequestBody Role role){
        return roleMenuService.addRoleMenu(role);
    }

    /**
     * 删除角色权限信息
     * @param roleId
     * @return
     */
    @ApiOperation("删除角色对应的权限信息")
    @RequestMapping("/delRoleMenu")
    public ResultInfo delUserRole(Integer roleId){
        return roleMenuService.delRoleMenu(roleId);
    }

    /**
     * 修改角色权限信息
     * @param role
     * @return
     */
    @ApiOperation("修改角色对应的权限信息")
    @RequestMapping("/updateRoleMenu")
    public ResultInfo updateRoleMenu(@RequestBody Role role){
        return roleMenuService.updateRoleMenu(role);
    }

    /**
     * 根据角色id查询权限信息
     */
    @ApiOperation("根据角色id查询权限信息")
    @RequestMapping("/selectMenuByRoleId")
    public ResultInfo selectMenuByRoleId(Integer roleId){
        System.out.println("进来没有");
        return roleMenuService.selectMenuByRoleId(roleId);
    }


}
