package com.wanxi.mapper;

import com.wanxi.pojo.Role;
import com.wanxi.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 用户角色管理
 * @author: fxf
 * @date: 2022/9/23 11:30
 */
@Mapper
public interface UserRoleMapper {

    /**
     * 新增用户角色信息
     * @return
     */
    int addUserRole(User user);


    /**
     * 删除用户角色信息
     * @param userId
     * @return
     */
    int delUserRole(Integer userId);

    /**
     * 修改用户角色信息
     * @param userId
     * @return
     */
    int updateUserRole(Integer userId);

    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(Integer userId);
}
