package com.wanxi.mapper;

import com.wanxi.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: fxf
 * @date: 2022/9/23 15:02
 */
@Mapper
public interface RoleMapper {
    /**
     * 新增角色信息
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    int delRole(Integer roleId);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 通过角色id查询角色信息
     * @param roleId
     * @return
     */
    List<Role> selectRoleById(Integer roleId);
}
