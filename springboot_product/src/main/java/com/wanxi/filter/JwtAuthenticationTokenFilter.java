package com.wanxi.filter;

import com.wanxi.pojo.LoginUser;
import com.wanxi.utils.JwtUtil;
import com.wanxi.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

/**
 * @description: JWT的认证过滤器
 * @author: fxf
 * @date: 2022/9/18 22:47
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //JWT的认证过滤器里面是干啥的？步骤
        // 1、从请求头获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //若没有token值则放行不再往下执行
            filterChain.doFilter(request, response);
            /**
             * 若这里什么都不做，不是请求登录接口时，它还是请求成功（没有登录就直接访问后端接口）
             *  该怎么办呢？
             */
            //返回时也不让它往下执行
            return;
        }

        // 2、解析token获取用户id
        String userId = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
            //若你在生成jwt时设置了过期时间，那么在这里获取jwt时就要去判断一下是否过期
        } catch (Exception e) {
            e.printStackTrace();
            //若传递过来的token值不能解析则抛出异常
            throw new RuntimeException("token值非法");
        }

        // 3、根据用户id从redis中获取用户信息
        String key = "login:"+userId;
        LoginUser loginUser = redisCache.getCacheObject(key);
        if (Objects.isNull(loginUser)){
            //token值能解析但在redis中却不存在即该用户是未登录状态
            throw new RuntimeException("该用户未登录");
        }

        // 4、把用户信息存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中，但现在还没有涉及等涉及了再来编写完整（第三个参数）
        //TODO 注意这个权限信息是从哪里获取到呢？--从redis中用户信息中
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        //存储
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 5、放行
        filterChain.doFilter(request,response);

    }
}
