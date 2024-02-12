package com.hh99.productservice.product.redis;

import com.hh99.productservice.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<Integer, Object> redisTemplate;

    public void saveProduct(ProductDTO product) {
        redisTemplate.opsForValue().set(product.getProductSeq(), product);
    }

    public ProductDTO getProduct(Integer productSeq) {
        return (ProductDTO) redisTemplate.opsForValue().get(productSeq);
    }

}
