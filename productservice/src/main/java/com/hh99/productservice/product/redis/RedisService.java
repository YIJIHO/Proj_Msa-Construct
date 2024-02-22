package com.hh99.productservice.product.redis;

import com.hh99.productservice.product.dto.ProductDTO;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private HashOperations<String, Integer, Integer> hashOperations;

    public void saveProduct(ProductDTO product) {
        hashOperations.put("product",product.getProductCode(), product.getProductStock());
    }

    public Integer getProduct(Integer productSeq) {
        return hashOperations.get("product",productSeq);
    }

    public void deleteProduct(Integer productSeq) {
        hashOperations.delete("product",productSeq);
    }
}
