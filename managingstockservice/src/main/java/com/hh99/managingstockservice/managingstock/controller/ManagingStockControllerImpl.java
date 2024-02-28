package com.hh99.managingstockservice.managingstock.controller;

import com.hh99.managingstockservice.managingstock.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manage-stock")
public class ManagingStockControllerImpl implements ManaginStockController{
    private final RedisService redisService;

    @Override
    @PostMapping
    public ResponseEntity<?> createProductStock(@RequestParam Integer productCode, @RequestParam Integer productStock){
        redisService.saveProduct(productCode,productStock);
        return ResponseEntity.ok().body("생성완료");
    }

    @Override
    @GetMapping
    public ResponseEntity<Integer> getProductStock(@RequestParam Integer productCode){
        return ResponseEntity.ok().body(redisService.getProductStock(productCode));
    }

    @Override
    @PutMapping
    public ResponseEntity<Boolean> changeProductStock(@RequestParam Integer productCode,@RequestParam Integer productStock,@RequestParam boolean changeStatus){
        return ResponseEntity.ok().body(redisService.changeStock(productCode,productStock,changeStatus));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Boolean> deleteProductStock(@RequestParam Integer productCode){
        return ResponseEntity.ok().body(redisService.deleteProduct(productCode));
    }
}
