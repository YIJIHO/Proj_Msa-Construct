package com.hh99.productservice.product.service;

import com.hh99.productservice.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    Boolean createProduct(ProductDTO product);
    List<ProductDTO> searchProductList();
    ProductDTO searchProductDetail(int productCode);
    ProductDTO searchProductStock(int productCode);
    Boolean deleteProduct(int productCode);
}
