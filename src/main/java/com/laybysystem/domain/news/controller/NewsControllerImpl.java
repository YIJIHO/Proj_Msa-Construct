package com.laybysystem.domain.news.controller;

import com.laybysystem.domain.news.dto.NewsDTO;
import com.laybysystem.domain.news.service.NewsService;
import com.laybysystem.domain.user.dto.UserDTO;
import com.laybysystem.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsControllerImpl implements NewsController{
    private final NewsService newsService;
    private final JwtProvider jwtProvider;

    @Override
    @GetMapping
    public ResponseEntity<?> searchNewsfeed(@RequestParam String token){
        UserDTO user = jwtProvider.getUserInfo(token);
        List<NewsDTO> newsFeed = newsService.searchNewsfeed(user.getUserSeq());
        if(newsFeed!=null){
            return ResponseEntity.ok(newsFeed);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("뉴스피드 목록이 없습니다.");
        }
    }
}
