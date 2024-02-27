package com.hh99.orderservice.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.serializer.Serializer;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO implements Serializable {
    private int orderSeq;
    private String orderCode;
    private int orderUserSeq;
    private int orderProductCode;//상품id
    private int orderStock;
    private Timestamp orderDate;
    private boolean orderStatus;//false 결제전 점유상태 true 결제후 점유상태
}
