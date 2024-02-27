package com.hh99.orderservice.order.service;

import com.hh99.orderservice.order.dto.OrderDTO;

public interface OrderService {
    Boolean createOrder(OrderDTO order);
    Boolean deleteOrder(OrderDTO order);
}
