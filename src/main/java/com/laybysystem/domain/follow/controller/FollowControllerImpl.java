package com.laybysystem.domain.follow.controller;

import com.laybysystem.domain.follow.dto.FollowDTO;
import com.laybysystem.domain.follow.service.FollowService;
import com.laybysystem.domain.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowControllerImpl implements FollowController{
    private final FollowService followService;
    private final NewsService newsService;
    @Override
    @PostMapping
    public ResponseEntity<String> createFollowship(@RequestBody FollowDTO follow){
        if(followService.createFollowship(follow)){
            newsService.createNewsLog(follow.getFollowerUserSeq(),follow.getFollowingUserSeq(),4);
            return ResponseEntity.ok("팔로우를 시작하셨습니다.");
        } else {
            return ResponseEntity.badRequest().body("팔로우에 실패하였습니다. 다시 시도해주세요.");
        }
    }
    @Override
    @GetMapping()
    public ResponseEntity<?> searchFollowship(@RequestBody FollowDTO follow) {
       List<FollowDTO> followList =  followService.searchFollowship(follow);
       if(followList!=null){
           return ResponseEntity.ok(followList);
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("팔로우목록이 없습니다.");
       }
    }
    @Override
    @DeleteMapping
    public ResponseEntity<String> deleteFollowship(@RequestBody FollowDTO follow) {
        if(followService.deleteFollowship(follow)){
            return ResponseEntity.ok("팔로우를 취소하였습니다.");
        } else {
            return ResponseEntity.badRequest().body("팔로우취소를 실패하였습니다. 다시 시도해주세요.");
        }

    }
}
