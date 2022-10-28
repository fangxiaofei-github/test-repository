package com.wanxi.controller;

import com.wanxi.pojo.User;
import com.wanxi.service.UserRoleService;
import com.wanxi.vo.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description: 用户角色管理
 * @author: fxf
 * @date: 2022/9/23 11:24
 */
@Api(tags = "用户角色管理控制层模块")
@RestController
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 给用户添加角色
     */
    @ApiOperation("给用户添加角色")
    @RequestMapping("/addUserRole")
    public ResultInfo addUserRole(@RequestBody User user){
        return userRoleService.addUserRole(user);
    }

    /**
     * 删除用户角色信息
     * @param userId
     * @return
     */
    @ApiOperation("删除用户对应的角色信息")
    @RequestMapping("/delUserRole")
    public ResultInfo delUserRole(Integer userId){
        return userRoleService.delUserRole(userId);
    }

    /**
     * 修改用户角色信息
     * @param user
     * @return
     */
    @ApiOperation("修改用户对应的角色信息")
    @RequestMapping("/updateUserRole")
    public ResultInfo updateUserRole(@RequestBody User user){
        return userRoleService.updateUserRole(user);
    }

    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    @ApiOperation("根据用户id查询角色信息")
    @RequestMapping("/findRoleByUserId")
    public ResultInfo findAll(Integer userId){
        return userRoleService.findAll(userId);
    }

}
