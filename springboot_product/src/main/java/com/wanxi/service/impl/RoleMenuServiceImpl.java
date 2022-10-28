package com.wanxi.service.impl;

import com.wanxi.mapper.RoleMenuMapper;
import com.wanxi.pojo.Menu;
import com.wanxi.pojo.Role;
import com.wanxi.service.RoleMenuService;
import com.wanxi.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 角色权限信息管理的业务层
 * @author: fxf
 * @date: 2022/9/23 15:37
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * 给角色添加权限
     * @param role
     * @return
     */
    @Transactional
    @Override
    public ResultInfo addRoleMenu(Role role) {
        ResultInfo info = new ResultInfo();
        // 参数校验
        // 添加相应的权限信息
        int row = roleMenuMapper.addRoleMenu(role);
        if (row < 1){
            info.setMsg("给角色添加权限失败");
            return info;
        }
        info.setMsg("给角色添加权限成功");
        return info;
    }

    /**
     * 删除角色权限信息
     * @param roleId
     * @return
     */
    @Override
    public ResultInfo delRoleMenu(Integer roleId) {
        ResultInfo info = new ResultInfo();
        // 参数校验
        // 添加相应的权限信息
        int row = roleMenuMapper.delRoleMenu(roleId);
        if (row < 1){
            info.setMsg("删除角色权限信息失败");
            return info;
        }
        info.setMsg("删除角色权限信息成功");
        return info;
    }

    /**
     * 修改角色权限信息
     * @param role
     * @return
     */
    @Override
    public ResultInfo updateRoleMenu(Role role) {
        ResultInfo info = new ResultInfo();
        //参数校验
        //先删除角色相关的权限信息
        int count = roleMenuMapper.delRoleMenu(role.getId());
        if (count < 1){
            info.setMsg("角色权限信息修改失败");
            return info;
        }
        //再添加角色权限相关的信息
        int row = roleMenuMapper.addRoleMenu(role);
        if (count < 1){
            info.setMsg("角色权限信息新增失败");
            return info;
        }
        info.setMsg("角色权限信息修改成功");
        return info;
    }

    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    @Override
    public ResultInfo selectMenuByRoleId(Integer roleId) {
        ResultInfo info = new ResultInfo();
        //参数校验
        List<Menu> menuList = roleMenuMapper.selectMenuByRoleId(roleId);
        if (menuList == null){
            info.setMsg("根据角色id查询权限信息失败");
            return info;
        }
        info.setData(menuList);
        info.setMsg("根据角色id查询权限信息成功");
        return info;
    }
}
