package com.wanxi.vo;

import lombok.*;

/**
 * @description: 统一的响应实体类
 * @author: fxf
 * @date: 2022/7/26 22:52
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResultInfo {

    private Integer code;
    private String msg;
    private Integer count;
    private Object data;

}
