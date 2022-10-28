package com.wanxi.mapper;

import com.wanxi.pojo.Menu;
import com.wanxi.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: fxf
 * @date: 2022/9/23 15:39
 */
@Mapper
public interface RoleMenuMapper {
    /**
     * 给角色添加权限
     * @param role
     * @return
     */
    int addRoleMenu(Role role);

    /**
     * 删除角色权限信息
     * @param roleId
     * @return
     */
    int delRoleMenu(Integer roleId);

    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    List<Menu> selectMenuByRoleId(Integer roleId);
}
