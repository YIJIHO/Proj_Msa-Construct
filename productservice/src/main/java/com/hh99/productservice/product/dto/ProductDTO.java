package com.hh99.productservice.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private int productSeq;
    private int productCode;
    private String productName;
    private String productPrice;
    private String productDescription;
    private String productType;
    private int productStock;
    private Timestamp productSellingTime;
    private boolean productOpenStatus;
}
