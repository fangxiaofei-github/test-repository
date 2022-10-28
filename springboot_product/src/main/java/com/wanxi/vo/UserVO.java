package com.wanxi.vo;

import lombok.*;

/**
 * @description: 用来接收用户信息的对象
 * @author: fxf
 * @date: 2022/8/27 15:42
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserVO {
    private String username;
    private String nickname;
    private String tel;
    private String status;
    private String page;
    private String limit;

    // 起始行索引
    private Integer currentIndex;
    // 每页显示多少条数据
    private Integer currentCount;

}
