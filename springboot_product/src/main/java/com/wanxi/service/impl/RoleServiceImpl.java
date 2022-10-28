package com.wanxi.service.impl;

import com.wanxi.mapper.RoleMapper;
import com.wanxi.pojo.Role;
import com.wanxi.service.RoleService;
import com.wanxi.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 角色信息业务层
 * @author: fxf
 * @date: 2022/9/23 14:58
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 新增角色信息
     * @param role
     * @return
     */
    @Override
    public ResultInfo addRole(Role role) {
        ResultInfo info = new ResultInfo();
        //参数校验
        int count = roleMapper.addRole(role);
        if (count>1){
            info.setMsg("新增失败");
            return info;
        }
        info.setMsg("新增成功");
        return info;
    }


    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    @Override
    public ResultInfo delRole(Integer roleId) {
        ResultInfo info = new ResultInfo();
        // 参数校验
        int count = roleMapper.delRole(roleId);
        if (count>1){
            info.setMsg("删除失败");
            return info;
        }
        info.setMsg("删除成功");
        return info;
    }

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    @Override
    public ResultInfo updateRole(Role role) {
        ResultInfo info = new ResultInfo();
        // 参数校验
        int count = roleMapper.updateRole(role);
        if (count>1){
            info.setMsg("修改失败");
            return info;
        }
        info.setMsg("修改成功");
        return info;
    }

    /**
     * 通过角色id查询角色信息
     * @param roleId
     * @return
     */
    @Override
    public ResultInfo selectRoleById(Integer roleId) {
        ResultInfo info = new ResultInfo();
        // 参数校验
        List<Role> roleList = roleMapper.selectRoleById(roleId);
        if (roleList == null){
            info.setMsg("查询失败");
            return info;
        }
        info.setData(roleList);
        info.setMsg("查询成功");
        return info;
    }
}
