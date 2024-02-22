package com.hh99.productservice.product.mapper;

import com.hh99.productservice.product.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int insertProduct(ProductDTO product);
    List<ProductDTO> selectProductList();
    ProductDTO selectProductDetail(int productCode);
    ProductDTO selectProductStock(int productCode);
    int deleteProduct(int productCode);
}
