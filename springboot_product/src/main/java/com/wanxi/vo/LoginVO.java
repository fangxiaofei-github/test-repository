package com.wanxi.vo;

import lombok.*;

/**
 * @description: 登录传递过来的参数信息对象
 * @author: fxf
 * @date: 2022/8/25 12:51
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginVO {

    //注意username和password的名字与security框架中默认的用户名与密码名一致
    private String username;
    private String password;
    private String remember;

}
