package com.hh99.productservice.product.service;

import com.hh99.productservice.product.dto.ProductDTO;
import com.hh99.productservice.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductMapper productMapper;

    @Override
    public Boolean createProduct(ProductDTO product){
        if(productMapper.insertProduct(product)==1){
            return true;
        } else {
            return false;
        }
    }
    @Override
    public List<ProductDTO> searchProductList(){
        List<ProductDTO> productList = productMapper.selectProductList();
        if(productList!=null){
            return productList;
        } else {
            return null;
        }
    }
    @Override
    public ProductDTO searchProductDetail(int productCode){
        ProductDTO product = productMapper.selectProductDetail(productCode);
        if(product!=null){
            return product;
        } else {
            return null;
        }
    }
    @Override
    public ProductDTO searchProductStock(int productCode){
        ProductDTO product = productMapper.selectProductStock(productCode);
        if(product!=null){
            return product;
        } else {
            return null;
        }
    }
    @Override
    public Boolean deleteProduct(int productCode){
        if(productMapper.deleteProduct(productCode)==1){
            return true;
        } else {
            return false;
        }
    }
}
