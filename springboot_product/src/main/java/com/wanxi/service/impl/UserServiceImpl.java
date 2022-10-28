package com.wanxi.service.impl;

import com.wanxi.mapper.UserMapper;
import com.wanxi.pojo.User;
import com.wanxi.service.UserService;
import com.wanxi.vo.ResultInfo;
import com.wanxi.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: fxf
 * @date: 2022/8/27 15:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //添加用户时的密码不再使用MD5的工具类去加密了，而是使用security框架的BCryptPasswordEncoder的encode()
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据查询条件和分页参数去查询信息
     * @param userVO
     * @return
     */
    @Override
    public ResultInfo selectByConditionAndPage(UserVO userVO) {
        ResultInfo info = new ResultInfo();
        // 获取起始下标
        Integer pageIndex = Integer.parseInt(userVO.getPage());
        Integer offset = Integer.parseInt(userVO.getLimit());
        Integer index = (pageIndex-1)*offset;
        userVO.setCurrentIndex(index);
        userVO.setCurrentCount(offset);

        // 调用持久层的相关方法
        List<User> users = userMapper.selectByConditionAndPage(userVO);
        if (users == null || users.size() < 1){
            info.setMsg("没有查询到信息！");
            return info;
        }
        int count = userMapper.findUserCount();
        info.setMsg("查询到信息！");
        info.setCode(0);
        info.setCount(count);
        info.setData(users);
        return info;
    }

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @Override
    public ResultInfo addUser(User user) {
        ResultInfo info = new ResultInfo();
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        // 让客服端传递过来的明文密码要进行加密（用密文存储）
        // String password = "123456"; //之前默认明文密码为123456。现在明文密码是前端传递过来的
        String password = user.getPassword();
        String encode = passwordEncoder.encode(password);
        user.setPassword(encode);

        //调用持久层的相关新增方法
        int row = userMapper.addUser(user);
        if (row < 1){
            info.setMsg("新增失败！");
            return info;
        }
        info.setMsg("新增成功！");
        return info;
    }

    /**
     * 删除用户信息（实际上是修改状态值）
     * @param ids
     * @return
     */
    @Override
    public ResultInfo delUser(String[] ids) {
        ResultInfo info = new ResultInfo();
        for (int i = 0; i < ids.length; i++) {
            userMapper.updateUserStatus(Integer.parseInt(ids[i]));
        }
        info.setMsg("删除成功！");
        return info;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Override
    public ResultInfo editUser(User user) {
        ResultInfo info = new ResultInfo();
        user.setUpdateTime(new Date());

        //修改密码时，要明文密码加密成密文
        String password = user.getPassword();
        String encode = passwordEncoder.encode(password);
        user.setPassword(encode);

        //调用修改方法
        int row = userMapper.editUser(user);

        if (row < 1){
            info.setMsg("修改失败！");
            return info;
        }
        info.setMsg("修改成功！");
        return info;
    }
}
