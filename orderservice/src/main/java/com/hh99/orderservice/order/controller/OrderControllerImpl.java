package com.hh99.orderservice.order.controller;

import com.hh99.orderservice.order.dto.OrderDTO;
import com.hh99.orderservice.order.global.ApiRequestService;
import com.hh99.orderservice.order.redis.RedisService;
import com.hh99.orderservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController{
    private final OrderService orderService;
    private final RedisService redisService;
    private final ApiRequestService apiRequestService;

    //주문생성
    @Override
    @PostMapping("/initail-order")
    public ResponseEntity<?> createOrder(@RequestParam String token,@RequestBody OrderDTO order){
        int orderUserSeq = apiRequestService.getUserSeq(token);
        order.setOrderUserSeq(orderUserSeq);
        if(apiRequestService.changeProductStock(order.getOrderProductCode(),order.getOrderStock(),true)){
            order = redisService.createOrder(order);
            orderService.createOrder(order);
            return ResponseEntity.ok().body(order);
        } else {
            return ResponseEntity.badRequest().body("해당 상품의 재고가 없거나 구매수량이 상품의 재고보다 많습니다.");
        }
    }

    //결제완료
    @Override
    @PostMapping("/purchase-order")
    public ResponseEntity<?> purchaseOrder(@RequestParam String orderCode){
        OrderDTO order = redisService.getOrder(orderCode);
        if(order!=null){
            if(orderService.createOrder(order)){
                redisService.changeOrderStatus(orderCode);
                return ResponseEntity.ok().body("결제를 완료하였습니다.");
            } else {
                return ResponseEntity.badRequest().body("결제에 실패하였습니다. 다시 시도해 주세요");
            }
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문내역이 없습니다.");
        }
    }
    //주문취소(결제전)
    @Override
    @DeleteMapping("/before-purchase")
    public ResponseEntity<String> cancelOrderBeforePurchase(@RequestParam String orderCode){
        OrderDTO order = redisService.getOrder(orderCode);
        if(order!=null){
            if(apiRequestService.changeProductStock(order.getOrderProductCode(),order.getOrderStock(),false)){
                redisService.deleteOrder(orderCode);
                return ResponseEntity.ok().body("진행 중인 주문을 취소하셨습니다.");
            } else {
                return ResponseEntity.ok().body("주문 취소에 실패하였습니다. 다시 시도해 주세요");
            }
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문내역이 없습니다.");
        }
    }
    //주문취소(결제후)
    @Override
    @DeleteMapping("/after-purchase")
    public ResponseEntity<String> cancelOrderAfterPurchase(@RequestParam String orderCode){
        OrderDTO order = redisService.getOrder(orderCode);
        if(order!=null){
            if(apiRequestService.changeProductStock(order.getOrderProductCode(),order.getOrderStock(),false)){
                orderService.deleteOrder(order);
                redisService.deleteOrder(orderCode);
                return ResponseEntity.ok().body("주문을 취소하셨습니다.");
            } else {
                return ResponseEntity.badRequest().body("주문취소에 실패하셨습니다. 다시 시도해주세요.");
            }
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문내역이 없습니다.");
        }
    }
    //주문조회
    @Override
    @GetMapping
    public ResponseEntity<?> searchOrder(@RequestParam String orderCode){
        OrderDTO order = redisService.getOrder(orderCode);
        if(order!=null){
            return ResponseEntity.ok().body(order);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문내역이 없습니다.");
        }
    }
}
