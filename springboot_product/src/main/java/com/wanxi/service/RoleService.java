package com.wanxi.service;

import com.wanxi.pojo.Role;
import com.wanxi.vo.ResultInfo;

/**
 * @description:
 * @author: fxf
 * @date: 2022/9/23 14:58
 */
public interface RoleService {

    /**
     * 新增角色信息
     */
    ResultInfo addRole(Role role);

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    ResultInfo delRole(Integer roleId);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    ResultInfo updateRole(Role role);

    /**
     * 通过角色id查询角色信息
     * @param roleId
     * @return
     */
    ResultInfo selectRoleById(Integer roleId);
}
