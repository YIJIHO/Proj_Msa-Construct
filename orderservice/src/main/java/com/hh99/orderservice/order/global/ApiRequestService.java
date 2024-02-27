package com.hh99.orderservice.order.global;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiRequestService {

    public Integer getUserSeq(String token){
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080")
                .path("/user/get-userseq")
                .queryParam("token", token)
                .toUriString();
        return restTemplate.getForObject(url, Integer.class);
    }
}
