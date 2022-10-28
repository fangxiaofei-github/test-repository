package com.wanxi.controller;

import com.wanxi.pojo.User;
import com.wanxi.service.UserService;
import com.wanxi.vo.ResultInfo;
import com.wanxi.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 用户信息的控制层
 * @author: fxf
 * @date: 2022/8/27 15:38
 */
@Api(tags = "用户信息的控制层模块")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据查询条件和分页参数去查询信息
     * @param userVO
     * @return
     */
    @ApiOperation("根据查询条件和分页参数去查询信息")
    @GetMapping("/userInfo")
    @PreAuthorize("hasAuthority('user:get')")
    public ResultInfo selectByConditionAndPage(UserVO userVO){
        // 根据查询条件和分页参数去查询用户信息
        ResultInfo info = userService.selectByConditionAndPage(userVO);
        return info;
    }

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @ApiOperation("添加用户信息")
    @PostMapping("/userInfoAdd")
    @PreAuthorize("hasAuthority('user:add')")
    public ResultInfo addUser(@RequestBody User user){
        // 调用业务的新增方法
        ResultInfo info = userService.addUser(user);
        return info;
    }

    /**
     * 删除用户信息（实际上是修改状态值）
     * @param ids
     * @return
     */
    @ApiOperation("删除用户信息（实际上是修改状态值）")
    @GetMapping("/userInfoDel")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResultInfo delUser(String ids){
        // 拆分参数
        String[] idArr = ids.split(",");
        //调用业务层的删除操作
        ResultInfo info = userService.delUser(idArr);
        return info;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @ApiOperation("修改用户信息")
    @PostMapping("/userInfoEdit")
    @PreAuthorize("hasAuthority('user:update')")
    public ResultInfo editUser(@RequestBody User user){
        // 调用业务的修改方法
        ResultInfo info = userService.editUser(user);
        return info;
    }





}
