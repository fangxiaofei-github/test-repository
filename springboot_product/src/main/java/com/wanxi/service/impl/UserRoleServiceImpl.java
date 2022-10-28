package com.wanxi.service.impl;

import com.wanxi.mapper.UserRoleMapper;
import com.wanxi.pojo.Role;
import com.wanxi.pojo.User;
import com.wanxi.service.UserRoleService;
import com.wanxi.vo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 用户角色管理的业务层
 * @author: fxf
 * @date: 2022/9/23 11:25
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 给用户添加角色
     * @return
     */
    @Override
    public ResultInfo addUserRole(User user) {
        ResultInfo info = new ResultInfo();
        // 参数校验
        // 调用业务层的相关方法
        int count = userRoleMapper.addUserRole(user);
        if (count < 1){
            info.setMsg("用户角色新增失败");
            return info;
        }
        info.setMsg("用户角色新增成功");
        return info;
    }

    /**
     * 删除用户角色信息
     * @param userId
     * @return
     */
    @Override
    public ResultInfo delUserRole(Integer userId) {
        ResultInfo info = new ResultInfo();
        //参数校验
        //调用相关的方法
        int count = userRoleMapper.delUserRole(userId);
        if (count < 1){
            info.setMsg("用户角色删除失败");
            return info;
        }
        info.setMsg("用户角色删除成功");
        return info;
    }


    /**
     * 修改用户角色信息
     * @param user
     * @return
     */
    @Transactional
    @Override
    public ResultInfo updateUserRole(User user) {
        ResultInfo info = new ResultInfo();
        //参数校验
        //先删除用户相关的角色信息
        int count = userRoleMapper.delUserRole(user.getId());
        if (count < 1){
            info.setMsg("用户角色修改失败");
            return info;
        }
        //再添加用户角色相关的信息
        int row = userRoleMapper.addUserRole(user);
        if (count < 1){
            info.setMsg("用户角色新增失败");
            return info;
        }
        info.setMsg("用户角色修改成功");
        return info;
    }


    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    @Override
    public ResultInfo findAll(Integer userId) {
        ResultInfo info = new ResultInfo();
        //参数校验
        // 根据用户id查询角色信息
        List<Role> roles = userRoleMapper.findRoleByUserId(userId);
        if (roles == null){
            info.setMsg("用户角色查询失败");
            return info;
        }
        info.setData(roles);
        info.setMsg("用户角色查询成功");
        return info;
    }
}
