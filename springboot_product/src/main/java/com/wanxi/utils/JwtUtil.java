package com.wanxi.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类（它是根据用户id去生成token的）
 */
public class JwtUtil {

    // 过期时间
    public static final Long JWT_TTL = 24*60 * 60 *1000L; //60 * 60 *1000  一个小时
    // 设置秘钥明文
    public static final String JWT_KEY = "sangeng";
    
    // 生成uuid字符串
    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }
    
    //========================================================================
    
    /**
     * 创建token【传递参数：主题(即token中要存放的数据)】
     */
    public static String createJWT(String subject) { // 参数表示token中要存放的数据（json格式）
        // 传递的过期时间为null，则会使用默认的过期时间JWT_TTL
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }
    /**
     * 创建token【传递参数：主题、过期时间】
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }
    /**
     * 创建token【传递参数：主题、过期时间、UUID】
     */
    public static String createJWT(String uuid, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, uuid);// 设置过期时间
        return builder.compact();
    }
    
    //====================================================================================
    
    

    //参数一： token中要存放的数据（json格式）, 参数二： token超时时间，参数三：UUID字符串
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        // 算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 获取到密钥SecretKey类型的
        SecretKey secretKey = generalKey();
        // 当前时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // 若传递过来的过期时间为null，设置默认的过期时间
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        // 过期时间设置为expDate
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        
        return Jwts.builder()
                .setId(uuid)         //唯一的ID 【声明的标识{"jti":"888"}---》属于payload中的标准声明】
                 //主体、用户{"sub":"Rose"}---》属于payload中的标准声明
                .setSubject(subject)   // 主题  【token中要存放的数据（json格式）】
                .setIssuer("sg")     // 签发者
                 //创建日期{"ita":"xxxxxx"}---》属于payload中的标准声明
                .setIssuedAt(now)      // 签发时间
                 //签名手段(参数1：算法，参数2：盐)---》这是jwt第三部分
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);  // 过期时间
    }





    /**
     * 生成加密后的秘钥 secretKey
     */
    public static SecretKey generalKey() {
        // java.util（jdk8推荐）Base64的解密方式
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        /**
         * SecretKeySpec类是KeySpec接口的实现类，用于构建秘密密钥规范。可根据一个字节数组构造一个SecretKey.
         *  问题：加密和解密时密钥类型用Key和SecretKeySpec有什么区别？？？
         *   答：key是你的byte数组定义的密钥， SecretKeySpec是采用某种加密算法加密后的密钥。
         */ 
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    
    /**
     * 解析token
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
    
    
    
    public static void main(String[] args) throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjYWM2ZDVhZi1mNjVlLTQ0MDAtYjcxMi0zYWEwOGIyOTIwYjQiLCJzdWIiOiJzZyIsImlzcyI6InNnIiwiaWF0IjoxNjM4MTA2NzEyLCJleHAiOjE2MzgxMTAzMTJ9.JVsSbkP94wuczb4QryQbAke3ysBDIL5ou8fWsbt_ebg";
        Claims claims = parseJWT(token);
        System.out.println(claims);
    }

}