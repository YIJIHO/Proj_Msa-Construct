package com.hh99.productservice.product.controller;

import com.hh99.productservice.product.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductController {
    ResponseEntity<String> createProduct(@RequestBody ProductDTO product);
    ResponseEntity<?> searchProductList();
    ResponseEntity<?> searchProductDetail(@RequestParam int productSeq);
    ResponseEntity<?> searchProductStock(@RequestParam int productSeq);
    ResponseEntity<String> deleteProduct(@RequestParam int productSeq);
}
