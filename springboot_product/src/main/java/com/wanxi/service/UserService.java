package com.wanxi.service;

import com.wanxi.pojo.User;
import com.wanxi.vo.ResultInfo;
import com.wanxi.vo.UserVO;

/**
 * @description: 用户信息的业务层
 * @author: fxf
 * @date: 2022/8/27 15:41
 */
public interface UserService {

    /**
     * 根据查询条件和分页参数去查询信息
     * @param userVO
     * @return
     */
    ResultInfo selectByConditionAndPage(UserVO userVO);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    ResultInfo addUser(User user);

    /**
     * 删除用户信息（实际上是修改状态值）
     * @param ids
     * @return
     */
    ResultInfo delUser(String[] ids);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    ResultInfo editUser(User user);
}
