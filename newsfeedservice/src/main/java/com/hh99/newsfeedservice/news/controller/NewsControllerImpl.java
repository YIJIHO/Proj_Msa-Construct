package com.hh99.newsfeedservice.news.controller;

import com.hh99.newsfeedservice.common.ApiRequestService;
import com.hh99.newsfeedservice.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsControllerImpl implements NewsController {
    private final NewsService newsService;
    private final ApiRequestService apiRequestService;

    @Override
    @PostMapping("/create-log")
    public ResponseEntity<?> createNewsLog(@RequestParam int newsLogCreator,@RequestParam int newsContentProvider,@RequestParam int newsLogType){
        newsService.createNewsLog(newsLogCreator,newsContentProvider,newsLogType);
        return ResponseEntity.ok().body("로그 생성 완료.");
    }



    @Override
    @GetMapping
    public ResponseEntity<?> searchNewsfeed(@RequestParam String token) {
        int userSeq = apiRequestService.getUserSeq(token);
        if (userSeq == 0) {
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            List<String> newsFeed = newsService.searchNewsfeed(userSeq);
            if (newsFeed != null) {
                return ResponseEntity.ok(newsFeed);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("뉴스피드 목록이 없습니다.");
            }
        }
    }
}
