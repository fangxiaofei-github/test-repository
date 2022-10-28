package com.wanxi.service.impl;

import com.wanxi.mapper.LoginMapper;
import com.wanxi.pojo.LoginUser;
import com.wanxi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.根据用户名去数据库中查询用户信息
        User user = loginMapper.findUserByUsername(username);

        /*
         * 这里好像并没有去比较密码是否正确？和lzj中的自定义登录逻辑是不一样的注意区分
         */

        // 2.判断是否存在该用户
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在！");
        }

        //TODO 3.查询用户权限封装起来。存储到LoginUser中去，最终它会响应到业务层的登录方法中Authentication
        List<String> perms = loginMapper.selectPermsByUserId(user.getId());


        // 4.返回响应信息，必须是UserDetails类型的
        LoginUser loginUser = new LoginUser(user , perms);
        return loginUser;
    }
}