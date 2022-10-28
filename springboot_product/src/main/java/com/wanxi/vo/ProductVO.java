package com.wanxi.vo;

import lombok.*;

/**
 * @description: 用来接收产品参数的对象
 * @author: fxf
 * @date: 2022/8/27 8:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductVO {
    private String productName;
    private String price_min;
    private String price_max;
    private String status;
    private String page;
    private String limit;


    // 起始行索引
    private Integer currentIndex;
    // 每页显示多少条数据
    private Integer currentCount;

}
