package com.colak.springtutorial.stringvalue.config;

import com.colak.springtutorial.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, User> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {

        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);

        // For value use custom RedisSerializer
        MessagePackRedisSerializer<User> messagePackRedisSerializer = new MessagePackRedisSerializer<>(User.class);
        redisTemplate.setValueSerializer(messagePackRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
