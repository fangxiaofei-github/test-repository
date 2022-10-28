package com.wanxi.vo;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @author: fxf
 * @date: 2022/8/27 17:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LeaveMsgVO {
    private String username;
    private String title;
    private String context;
    private Integer status;
    //处理该条留言信息的用户ID
    private Integer userId;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String page;
    private String limit;

    // 起始行索引
    private Integer currentIndex;
    // 每页显示多少条数据
    private Integer currentCount;
}
