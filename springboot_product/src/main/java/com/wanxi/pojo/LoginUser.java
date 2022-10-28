package com.wanxi.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* 从上面的自定义登录逻辑可知，用户去登录时，它封装成了LoginUser对象，
 *  到时候框架内部会去调用LoginUser的getPassword()和getUsername()方法，
 *   它就会去实际从数据库中查询出来的User对象中用户名和密码。若下面的方法中不去调用那么会出现问题。
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    // 封装User对象（自己的user实体类对象）
    private User user;

    // 存储权限信息的（UserDetailsService实现类传递过来的）
    private List<String> permissions;


    public LoginUser(User user, List<String> permissions){
        this.user = user;
        this.permissions = permissions;
    }


    // 存储springsecurity所需要的权限信息集合
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    // 用来获取用户对应的权限信息的
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null){
            return authorities;
        }

        // 把传递过来的权限信息封装到springsecurity所需要的权限集合中authorities
        authorities = new ArrayList<>();
        for (String permission : permissions) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission);
            authorities.add(simpleGrantedAuthority);
        }
        return authorities;
    }


    //让框架获取当前用户的密码，若获取不到的话肯定是要出现问题
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //让框架获取当前用户的用户名，若获取不到的话肯定是要出现问题
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}