package com.wyf.securitydemo01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * <p>
 *
 * </p>
 *
 * @author wyf
 * @date 2021/5/14
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) throws UnknownHostException {
        //为了开发方便，采用<String,Object>
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        //String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();


        //key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //value序列化方式采用jackson
        template.setValueSerializer(new RedisSerializeUtil());
        //hash的value采用...的序列化方式
        template.setHashValueSerializer(new RedisSerializeUtil());

        template.afterPropertiesSet();
        return template;
    }


}
