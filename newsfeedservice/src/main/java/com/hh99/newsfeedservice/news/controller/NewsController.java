package com.hh99.newsfeedservice.news.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface NewsController {
    ResponseEntity<?> searchNewsfeed(@RequestParam String token);
    ResponseEntity<?> createNewsLog(@RequestParam int newsLogCreator,@RequestParam int newsContentProvider,@RequestParam int newsLogType);
}
