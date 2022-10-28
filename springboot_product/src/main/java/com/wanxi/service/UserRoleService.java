package com.wanxi.service;

import com.wanxi.pojo.User;
import com.wanxi.vo.ResultInfo;

/**
 * @description: 用户角色管理
 * @author: fxf
 * @date: 2022/9/23 11:25
 */
public interface UserRoleService {

    /**
     * 给用户添加角色
     * @return
     */
    ResultInfo addUserRole(User user);

    /**
     * 删除用户角色信息
     * @param userId
     * @return
     */
    ResultInfo delUserRole(Integer userId);

    /**
     * 修改用户角色信息
     * @param user
     * @return
     */
    ResultInfo updateUserRole(User user);

    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    ResultInfo findAll(Integer userId);
}
