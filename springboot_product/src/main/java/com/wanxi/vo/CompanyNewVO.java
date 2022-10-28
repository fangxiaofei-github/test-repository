package com.wanxi.vo;

import lombok.*;

/**
 * @description: 用来接收前端传递过来的公司新闻信息
 * @author: fxf
 * @date: 2022/8/26 11:32
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyNewVO {

    private String title;
    private String newTime;
    private String type;
    private String page; // 第几页数据
    private String limit; // 每页显示多少条数据


    // 起始行索引
    private Integer currentIndex;
    // 每页显示多少条数据
    private Integer currentCount;

}
