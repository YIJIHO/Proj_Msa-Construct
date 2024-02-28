package com.hh99.managingstockservice.managingstock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface ManaginStockController {
    ResponseEntity<?> createProductStock(@RequestParam Integer productCode, @RequestParam Integer productStock);
    ResponseEntity<Integer> getProductStock(@RequestParam Integer productCode);
    ResponseEntity<?> changeProductStock(@RequestParam Integer productCode,@RequestParam Integer productStock,@RequestParam boolean changeStatus);
    ResponseEntity<?> deleteProductStock(@RequestParam Integer productCode);

}
