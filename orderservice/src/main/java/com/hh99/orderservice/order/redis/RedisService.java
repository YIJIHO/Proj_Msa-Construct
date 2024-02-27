package com.hh99.orderservice.order.redis;

import com.hh99.orderservice.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class RedisService {

    @Qualifier("orderHashTemplate")
    @Autowired
    private HashOperations<String, String, OrderDTO> orderHashTemplate;

    @Qualifier("productHashTemplate")
    @Autowired
    private HashOperations<String, Integer, Integer> productHashTemplate;

    public OrderDTO createOrder(OrderDTO order){
        Integer currStock = getStock(order.getOrderProductCode());
        if(currStock!=null){
            String dateForKey = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
            String key = dateForKey+order.getOrderUserSeq()+order.getOrderProductCode();
            String secretkey = String.valueOf(new Random(key.hashCode()).nextLong());

            order.setOrderCode(secretkey);
            productHashTemplate.put("product",order.getOrderProductCode(),currStock-order.getOrderStock());
            orderHashTemplate.put("order",order.getOrderCode(),order);
            return order;
        } else {
            return null;
        }
    }

    public OrderDTO getOrder(String orderCode){
       return orderHashTemplate.get("order",orderCode);
    }

    public void changeOrderStatus(String orderCode){
        OrderDTO order = getOrder(orderCode);
        order.setOrderStatus(true);
        orderHashTemplate.put("order",order.getOrderCode(),order);
    }

    public void deleteOrder(String orderCode) {
        OrderDTO order = getOrder(orderCode);
        orderHashTemplate.delete("order",orderCode);
        Integer currStock = getStock(order.getOrderProductCode());
        if(currStock!=null){
            productHashTemplate.put("product",order.getOrderProductCode(),currStock+order.getOrderStock());
        }
    }

//    public boolean changeProductStock(OrderDTO order, boolean changeStatus){
//        Integer currStock = getStock(order.getOrderProductCode());
//        if(changeStatus){
//            if(currStock==null || currStock==0){
//                return false;
//            } else {
//                productHashTemplate.put("product",order.getOrderProductCode(),currStock-order.getOrderStock());
//                return true;
//            }
//        } else {
//            productHashTemplate.put("product",order.getOrderProductCode(),currStock+order.getOrderStock());
//            return true;
//        }
//    }

    public Integer getStock(Integer productCode){
        return productHashTemplate.get("product",productCode);
    }

}
