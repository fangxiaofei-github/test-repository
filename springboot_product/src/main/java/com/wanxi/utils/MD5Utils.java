package com.wanxi.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @description: MD5加密的工具类
 *       若我们使用了springsecurity框架，那么加密就是使用BCryptPasswordEncoder的encode()方法去加密
 *       这个工具类就不再使用了
 * @author: fxf
 * @date: 2022/7/27 0:38
 */
public class MD5Utils {

    public static String encryptToMD5(String value){
        return DigestUtils.md5Hex(value);
    }

    public static void main(String[] args) {
        //默认密码都是123456
        String result = encryptToMD5("123456");
        System.out.println(result);

    }
}
