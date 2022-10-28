package com.wanxi.service.impl;

import com.wanxi.mapper.LoginMapper;
import com.wanxi.pojo.LoginUser;
import com.wanxi.pojo.User;
import com.wanxi.service.LoginService;
import com.wanxi.utils.JwtUtil;
import com.wanxi.utils.MD5Utils;
import com.wanxi.utils.RedisCache;
import com.wanxi.vo.LoginVO;
import com.wanxi.vo.ResultInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @description:
 * @author: fxf
 * @date: 2022/8/25 18:21
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResultInfo login(String username, String password) {
        //ResultInfo中状态码值:code=1表示跳转到登录页，code=2表示跳转到首页
        ResultInfo info = new ResultInfo();
        User obj = new User();
        obj.setUsername(username);
        obj.setPassword(password);
        //参数的非空判断
        if (username == null || "".equals(username)){
            info.setCode(1);
            info.setMsg("用户名不能为空！");
            info.setData(obj);
            return info;
        }
        if (password == null || "".equals(password)){
            info.setCode(1);
            info.setMsg("密码不能为空！");
            info.setData(obj);
            return info;
        }

        //根据用户名去数据库中获取用户信息
        User user = loginMapper.findUserByUsername(username);
        if (user == null){
            info.setCode(1);
            info.setMsg("该用户不存在！请注册在登录！");
            info.setData(obj);
            return info;
        }
        //比较密码是否一致。
        //if (!user.getPassword().equals(MD5Utils.encryptToMD5(password))){
        //    info.setCode(1);
        //    info.setMsg("密码不正确！请重新输入！");
        //    info.setData(obj);
        //    return info;
        //}

        // 之前是使用MD5加密，现在使用了security框架了就使用PasswordEncoder的encode()方法加密和校验
        if (!user.getPassword().equals(passwordEncoder.matches(password,user.getPassword()))){
            info.setCode(1);
            info.setMsg("密码不正确！请重新输入！");
            info.setData(obj);
            return info;
        }

        //登录成功
        info.setCode(2);
        info.setMsg("登录成功！");
        info.setData(user);

        return info;
    }




    /**
     * security需要的自定义登录业务层方法
     */
    @Override
    public ResultInfo userLogin(LoginVO loginVO) {
        ResultInfo info = new ResultInfo();

        //1.调用AuthenticationManager接口中的方法authenticate()进行认证
        // TODO 注意自定义登录逻辑中获取到的权限信息封装到LoginUser中，最终会响应到这里
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVO.getUsername(),loginVO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //2.如果认证不通过就抛出一个自定义异常
        if (Objects.isNull(authentication)){
            info.setCode(1);
            info.setMsg("登录失败");
            return info;
            //throw new RuntimeException("用户名或密码错误");
        }

        //3.如果认证通过就生成jwt，根据userID生成jwt
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        // 注意这里生成一个jwt，一般都要jwt设置过期时间的，但这里却没有设置。
        // 注意：若在生成JWT时设置了过期时间，那么在JWT的认证过滤器中就要判断一下从前端传递过来的token是否过期了
        String jwt = JwtUtil.createJWT(userId);

        //4.把用户信息存储到Redis中去
        redisCache.setCacheObject("login:"+userId, loginUser);

        // TODO 当你登录成功后该你的QQ邮箱发送一封邮件表示欢迎 [参数1为交换机名称，2为路由键，3为想要发送的消息]
        // 把用户名和邮箱信息传递过去
        String value = loginUser.getUser().getNickname()+"-"+loginUser.getUser().getEmail();
        rabbitTemplate.convertAndSend("mail_ex","mailKey", value);

        //5.响应jwt给前端即把jwt存入到统一响应的实体类中，然后把统一响应的实体类响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token", jwt);
        info.setCode(2);
        info.setMsg("登录成功");
        info.setData(map);
        return info;
    }


    /**
     * security 退出登录
     */
    @Override
    public ResultInfo userLogout() {
        ResultInfo info = new ResultInfo();
        //1、从SecurityContextHolder中获取登录状态的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //2、从redis中删除用户id对应的用户信息
        String userId = loginUser.getUser().getId().toString();
        redisCache.deleteObject(userId);
        //3、响应信息
        info.setCode(200);
        info.setMsg("退出登录成功！");
        return info;
    }
    
}
