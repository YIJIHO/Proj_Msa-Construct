package com.hh99.managingstockservice.managingstock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisService {


    @Qualifier("productHashTemplate")
    @Autowired
    private HashOperations<String, Integer, Integer> productHashTemplate;

    public void saveProduct(Integer productCode,Integer productStock) {
        productHashTemplate.put("product",productCode, productStock);
    }

    public Integer getProductStock(Integer productCode){
        return productHashTemplate.get("product",productCode);
    }

    @Transactional
    public boolean changeStock(Integer productCode,Integer changeStock,boolean changeStatus){
        if(changeStatus){
            try {
                Integer currStock = getProductStock(productCode);
                if(currStock == null || currStock == 0 || currStock<changeStock){
                    throw new IllegalStateException();
                } else {
                    saveProduct(productCode,currStock-changeStock);
                    return true;
                }
            } catch (IllegalStateException e){
                return false;
            }
        } else {
            try {
                Integer currStock = getProductStock(productCode);
                if(currStock==null){
                    throw new IllegalStateException();
                } else {
                    saveProduct(productCode,getProductStock(productCode)+changeStock);
                    return true;
                }
            } catch (IllegalStateException e){
                return false;
            }
        }
    }

    public Boolean deleteProduct(Integer productCode) {
        productHashTemplate.delete("product",productCode);
        if(productHashTemplate.get("product",productCode)==null) {
            return true;
        } else {
            return false;
        }
    }
}
