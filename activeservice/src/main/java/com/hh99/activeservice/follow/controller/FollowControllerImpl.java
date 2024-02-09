package com.hh99.activeservice.follow.controller;


import com.hh99.activeservice.common.ApiRequestService;
import com.hh99.activeservice.follow.dto.FollowDTO;
import com.hh99.activeservice.follow.service.FollowService;
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
    private final ApiRequestService apiRequestService;

    @Override
    @PostMapping
    public ResponseEntity<String> createFollowship(@RequestParam String token,@RequestParam int followingSeq){
        int userSeq = apiRequestService.getUserSeq(token);
        if(userSeq==0){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if (followService.createFollowship(userSeq,followingSeq)) {
                apiRequestService.createNewsLog(userSeq,followingSeq,4);
                return ResponseEntity.ok("팔로우를 시작하셨습니다.");
            } else {
                return ResponseEntity.badRequest().body("팔로우에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }
    @Override
    @GetMapping()
    public ResponseEntity<?> searchFollowship(@RequestParam String token) {
        int userSeq = apiRequestService.getUserSeq(token);
        if(userSeq==0){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            List<FollowDTO> followList = followService.searchFollowship(userSeq);
            if (followList != null) {
                return ResponseEntity.ok(followList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("팔로우목록이 없습니다.");
            }
        }
    }
    @Override
    @DeleteMapping
    public ResponseEntity<String> deleteFollowship(@RequestParam String token,@RequestParam int followingSeq) {
        int userSeq = apiRequestService.getUserSeq(token);
        if(userSeq==0){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if (followService.deleteFollowship(userSeq,followingSeq)) {
                return ResponseEntity.ok("팔로우를 취소하였습니다.");
            } else {
                return ResponseEntity.badRequest().body("팔로우취소를 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }
}
