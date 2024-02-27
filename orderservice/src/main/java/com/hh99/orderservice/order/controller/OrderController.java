package com.hh99.orderservice.order.controller;

import com.hh99.orderservice.order.dto.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderController {
    ResponseEntity<?> createOrder(@RequestParam String token,@RequestBody OrderDTO order);
    ResponseEntity<?> purchaseOrder(@RequestParam String orderCode);
    ResponseEntity<String> cancelOrderBeforePurchase(@RequestParam String orderCode);
    ResponseEntity<String> cancelOrderAfterPurchase(@RequestParam String orderCode);
    ResponseEntity<?> searchOrder(@RequestParam String orderCode);
}
