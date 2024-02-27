package com.hh99.orderservice.order.redis;

import com.hh99.orderservice.order.dto.OrderDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean("productTemplate")
    public RedisTemplate<String, Integer> productTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericToStringSerializer<>(Integer.class));
        return template;
    }

    @Bean("productHashTemplate")
    public HashOperations<String, Integer, Integer> productHashTemplate(RedisTemplate<String, Integer> productTemplate) {
        return productTemplate.opsForHash();
    }

    @Bean("orderTemplate")
    public RedisTemplate<String, OrderDTO> orderTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, OrderDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean("orderHashTemplate")
    public HashOperations<String, String, OrderDTO> orderHashTemplate(RedisTemplate<String, OrderDTO> orderTemplate) {
        return orderTemplate.opsForHash();
    }

}