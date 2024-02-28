package com.hh99.productservice.product.global;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiRequestService {

    public Boolean setProductStock(Integer productCode,Integer productStock){
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8086")
                .path("/manage-stock")
                .queryParam("productCode", productCode)
                .queryParam("productStock", productStock)
                .toUriString();

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(null, new HttpHeaders()),
                Boolean.class
        );

        return responseEntity.getBody();
    }

    public Integer getProductStock(Integer productCode){
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080")
                .path("/manage-stock")
                .queryParam("productCode", productCode)
                .toUriString();

        ResponseEntity<Integer> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                Integer.class
        );

        return responseEntity.getBody();
    }
    public Boolean deleteProductStock(Integer productCode){
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8086")
                .path("/manage-stock")
                .queryParam("productCode", productCode)
                .toUriString();

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(null, new HttpHeaders()),
                Boolean.class
        );

        return responseEntity.getBody();
    }
}
