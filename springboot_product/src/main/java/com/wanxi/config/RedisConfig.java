package com.wanxi.config;

import com.wanxi.utils.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description: Redis相关的配置
 * @author: fxf
 * @date: 2022/9/17 18:32
 *    注意：这样做的目的是防止我们存储到redis当中那些key看上去像乱码的情况
 */
@Configuration
public class RedisConfig {

    @Bean  //参数是RedisConnectionFactory
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        /**
         * Redis使用FastJson序列化，所以我们创建FastJsonRedisSerializer类。【看FastJsonRedisSerializer】
         *  1.使用fastjson来序列化redis的value需要去创建一个类去重新实现RedisSerializer<T>接口并且重写方法，方法
         *    中使用fastjson格式去序列化值即可。
         *  2.若使用springboot默认的Jackson去序列化就不需要重写了，直接使用new GenericJackson2JsonRedisSerializer()
         *    对象去序列化redis的value值。
         */
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        /**
         * String
         */
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        // 使用fastjson来序列化redis的value
        template.setValueSerializer(serializer);
        /**
         * hash
         */
        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}
