package com.wanxi.service;

import com.wanxi.pojo.Role;
import com.wanxi.vo.ResultInfo;

/**
 * @description:
 * @author: fxf
 * @date: 2022/9/23 15:37
 */
public interface RoleMenuService {

    /**
     * 给角色添加权限
     * @param role
     * @return
     */
    ResultInfo addRoleMenu(Role role);

    /**
     * 删除角色权限信息
     * @param roleId
     * @return
     */
    ResultInfo delRoleMenu(Integer roleId);

    /**
     * 修改角色权限信息
     * @param role
     * @return
     */
    ResultInfo updateRoleMenu(Role role);

    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    ResultInfo selectMenuByRoleId(Integer roleId);
}
