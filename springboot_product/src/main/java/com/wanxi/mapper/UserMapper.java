package com.wanxi.mapper;

import com.wanxi.pojo.User;
import com.wanxi.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 用户信息的持久层
 * @author: fxf
 * @date: 2022/8/27 15:46
 */
@Mapper
public interface UserMapper {

    /**
     * 根据查询条件和分页参数去查询信息
     * @param userVO
     * @return
     */
    List<User> selectByConditionAndPage(UserVO userVO);

    /**
     * 查询一共有多少条记录
     * @return
     */
    int findUserCount();

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 把用户的status改为0
     * @param is
     */
    void updateUserStatus(int is);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int editUser(User user);

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    List<User> selectUserById(Integer userId);
}
