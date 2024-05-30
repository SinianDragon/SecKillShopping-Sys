package com.sks.secondkillstore.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author HQD
 * @Date 2024/4/20 17:13
 * @Version 1.0
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> stringObjectRedisTemplate = new RedisTemplate<>();
        //key的序列化
        stringObjectRedisTemplate.setKeySerializer(new StringRedisSerializer());
        //value的序列化
        stringObjectRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        stringObjectRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        stringObjectRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        stringObjectRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringObjectRedisTemplate;
    }

    @Bean
    public DefaultRedisScript<Long> script(){
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        //lock.lua脚本位置和application.yml同级目录
        redisScript.setLocation(new ClassPathResource("stock.lua"));
        redisScript.setResultType(Long.class);
        return redisScript;
    }
}
