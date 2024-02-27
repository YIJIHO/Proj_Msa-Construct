package com.hh99.orderservice.order.service;

import com.hh99.orderservice.order.dto.OrderDTO;
import com.hh99.orderservice.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderMapper orderMapper;

    @Override
    public Boolean createOrder(OrderDTO order){
        order.setOrderStatus(true);
        if(orderMapper.insertOrder(order)==1){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Boolean deleteOrder(OrderDTO order){
        if(orderMapper.deleteOrder(order)==1){
            return true;
        } else {
            return false;
        }
    }
}
