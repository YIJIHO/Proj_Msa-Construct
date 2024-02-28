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
        String url = UriComponentsBuilder.fromHttpUrl("http://managingstockservice:8086")
                .path("/manage-stock")
                .queryParam("productCode", productCode)
                .queryParam("productStock", productStock)
                .toUriString();
        String check = restTemplate.postForObject(url,null,String.class);
        if(check.equals("상품이 성공적으로 등록되었습니다.")){
            return true;
        } else{
            return false;
        }
//        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                new HttpEntity<>(null, new HttpHeaders()),
//                Boolean.class
//        );
//
//        return responseEntity.getBody();
    }

    public Integer getProductStock(Integer productCode){
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://managingstockservice:8086")
                .path("/manage-stock")
                .queryParam("productCode", productCode)
                .toUriString();

        return restTemplate.getForObject(url,Integer.class);

//        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity(url,Integer.class);
//
//        return responseEntity.getBody();
    }
    public Boolean deleteProductStock(Integer productCode){
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://managingstockservice:8086")
                .path("/manage-stock")
                .queryParam("productCode", productCode)
                .toUriString();

        restTemplate.delete(url);
        return true;
//        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.DELETE,
//                new HttpEntity<>(null, new HttpHeaders()),
//                Boolean.class
//        );
//
//        return responseEntity.getBody();
    }
}
