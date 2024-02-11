package com.hh99.productservice.product.service;

import com.hh99.productservice.product.dto.ProductDTO;
import com.hh99.productservice.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private ProductMapper productMapper;

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
    public ProductDTO searchProductDetail(int productSeq){
        ProductDTO product = productMapper.selectProductDetail(productSeq);
        if(product!=null){
            return product;
        } else {
            return null;
        }
    }
    @Override
    public ProductDTO searchProductStock(int productSeq){
        ProductDTO product = productMapper.selectProductStock(productSeq);
        if(product!=null){
            return product;
        } else {
            return null;
        }
    }
    @Override
    public Boolean deleteProduct(int productSeq){
        if(productMapper.deleteProduct(productSeq)==1){
            return true;
        } else {
            return false;
        }
    }
}
