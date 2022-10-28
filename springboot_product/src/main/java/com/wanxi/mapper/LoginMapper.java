package com.wanxi.mapper;

import com.wanxi.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 登录相关的持久层
 * @author: fxf
 * @date: 2022/8/25 18:25
 */
@Mapper
public interface LoginMapper {

    /**
     * 根据用户名去查询用户信息
     * @param username
     * @return
     */
    User findUserByUsername(String username);


    /**
     * 根据用户id去查询到其所对应的权限信息
     * @param userId
     * @return
     */
    List<String> selectPermsByUserId(Integer userId);
}
