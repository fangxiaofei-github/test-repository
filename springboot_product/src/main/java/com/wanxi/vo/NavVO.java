package com.wanxi.vo;

import lombok.*;

/**
 * @description: 用来接收导航栏信息的对象
 * @author: fxf
 * @date: 2022/8/26 22:31
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class NavVO {
    private String navName;
    private String type;
    private String status;
    private String page; // 第几页数据
    private String limit;  // 每页显示多少条数据


    // 起始行索引
    private Integer currentIndex;
    // 每页显示多少条数据
    private Integer currentCount;

}
