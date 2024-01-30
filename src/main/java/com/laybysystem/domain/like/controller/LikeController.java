package com.laybysystem.domain.like.controller;

import com.laybysystem.domain.comment.dto.CommentDTO;
import com.laybysystem.domain.post.dto.PostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface LikeController {
    ResponseEntity<String> createPostLikeSet(@RequestParam String token, @RequestBody PostDTO post);
    ResponseEntity<String> createCommentLikeSet(@RequestParam String token, @RequestBody CommentDTO comment);
    ResponseEntity<String> deletePostLikeSet(@RequestParam String token, @RequestBody PostDTO post);
    ResponseEntity<String> deleteCommentLikeSet(@RequestParam String token, @RequestBody CommentDTO comment);
}
