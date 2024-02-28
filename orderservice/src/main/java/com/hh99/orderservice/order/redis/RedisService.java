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
            String dateForKey = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
            String key = dateForKey+order.getOrderUserSeq()+order.getOrderProductCode();
            String secretkey = String.valueOf(new Random(key.hashCode()).nextLong());

            order.setOrderCode(secretkey);
            orderHashTemplate.put("order",order.getOrderCode(),order);
            return order;
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
    }
}
