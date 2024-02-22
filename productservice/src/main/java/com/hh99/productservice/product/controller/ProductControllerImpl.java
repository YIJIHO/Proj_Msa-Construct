package com.hh99.productservice.product.controller;

import com.hh99.productservice.product.dto.ProductDTO;
import com.hh99.productservice.product.service.ProductService;
import com.hh99.productservice.product.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductControllerImpl implements ProductController{
    private final ProductService productService;
    private final RedisService redisService;

    @Override
    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO product){
        if(productService.createProduct(product)){
            redisService.saveProduct(product);
            return ResponseEntity.ok().body("상품이 성공적으로 등록되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("상품등록에 실패하였습니다. 다시 시도해주세요.");
        }
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<?> searchProductList(){
        List<ProductDTO> productList = productService.searchProductList();
        if(productList!=null){
            return ResponseEntity.ok().body(productList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("상품목록을 찾을 수 없습니다.");
        }
    }

    @Override
    @GetMapping("/detail")
    public ResponseEntity<?> searchProductDetail(@RequestParam int productCode){
        ProductDTO product = productService.searchProductDetail(productCode);
        if(product!=null){
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 상품을 찾을 수 없습니다.");
        }
    }

    @Override
    @GetMapping("/stock")
    public ResponseEntity<?> searchProductStock(@RequestParam int productCode){
        Integer productStock = redisService.getProduct(productCode);
        if(productStock==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 상품을 찾을 수 없습니다.");
        } else if (productStock==0) {
            return ResponseEntity.ok().body("해당 상품의 재고가 없습니다.");
        } else {
            ProductDTO product = new ProductDTO();
            product.setProductSeq(productCode);
            product.setProductStock(productStock);
            return ResponseEntity.ok().body(product);
        }
    }

    @Override
    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(@RequestParam int productCode){
        if(productService.deleteProduct(productCode)){
            redisService.deleteProduct(productCode);
            return ResponseEntity.ok().body("상품이 삭제되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("상품 삭제에 실패하였습니다. 다시 시도해주세요.");
        }
    }

}
