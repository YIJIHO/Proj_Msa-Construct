package com.hh99.orderservice.order.mapper;

import com.hh99.orderservice.order.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int insertOrder(OrderDTO order);
    int deleteOrder(OrderDTO order);
}
