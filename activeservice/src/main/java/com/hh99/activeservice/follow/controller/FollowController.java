package com.hh99.activeservice.follow.controller;

import com.hh99.activeservice.follow.dto.FollowDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface FollowController {
    ResponseEntity<String> createFollowship(@RequestParam String token, @RequestParam int followingSeq);
    ResponseEntity<?> searchFollowship(@RequestParam String token);
    ResponseEntity<String> deleteFollowship(@RequestParam String token,@RequestParam int followingSeq);
}
