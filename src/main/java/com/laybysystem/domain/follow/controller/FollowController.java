package com.laybysystem.domain.follow.controller;

import com.laybysystem.domain.follow.dto.FollowDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface FollowController {
    ResponseEntity<String> createFollowship(FollowDTO follow);
    ResponseEntity<?> searchFollowship(@RequestBody FollowDTO follow);
    ResponseEntity<String> deleteFollowship(FollowDTO follow);
}
