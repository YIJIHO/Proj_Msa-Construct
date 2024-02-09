package com.hh99.activeservice.common;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiRequestService {

    public Integer getUserSeq(String token){
        System.out.println("apireq진입");
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://userservice:8081")
                .path("/user/get-userseq")
                .queryParam("token", token)
                .toUriString();
        return restTemplate.getForObject(url, Integer.class);
    }
    public void createNewsLog(int newsLogCreator,int newsContentProvider,int newsLogType){
        //newsfeed 생성할때 컨트롤러에서 이 요청 받을 준비 해야함
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://newsfeedservice:8083")
                .path("/news/create-log")
                .queryParam("newsLogCreator", newsLogCreator)
                .queryParam("newsContentProvider",newsContentProvider)
                .queryParam("newsLogType",newsLogType)
                .toUriString();
        String response = restTemplate.postForObject(url, null, String.class);
        System.out.println(response);
    }
}
