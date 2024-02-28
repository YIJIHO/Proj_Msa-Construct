package com.hh99.orderservice.order.global;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiRequestService {
    public Integer getUserSeq(String token){
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://userservice:8081")
                .path("/user/get-userseq")
                .queryParam("token", token)
                .toUriString();

        return restTemplate.getForObject(url,Integer.class);
    }

    public Boolean changeProductStock(Integer productCode,Integer productStock,boolean changeStatus){
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://managingstockservice:8086")
                .path("/manage-stock/change")
                .queryParam("productCode", productCode)
                .queryParam("productStock", productStock)
                .queryParam("changeStatus", changeStatus)
                .toUriString();

//        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.PUT,
//                new HttpEntity<>(null, new HttpHeaders()),
//                Boolean.class
//        );
//
//        return responseEntity.getBody();

        return restTemplate.postForObject(url,null,Boolean.class);

    }

}
