package com.wanxi.vo;

import lombok.*;

/**
 * @description: 用来接收传递过来的公司信息的对象
 * @author: fxf
 * @date: 2022/8/25 21:49
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyVO {
    private String companyName;
    private String address;
    private String hotLineTel;
    private String page; // 第几页数据
    private String limit; // 每页显示多少条数据


    // 起始行索引
    private Integer currentIndex;
    // 每页显示多少条数据
    private Integer currentCount;
}
