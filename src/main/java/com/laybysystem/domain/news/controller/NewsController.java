package com.laybysystem.domain.news.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface NewsController {
    ResponseEntity<?> searchNewsfeed(@RequestParam String token);
}
