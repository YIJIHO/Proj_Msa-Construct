package com.laybysystem.domain.like.controller;

import com.laybysystem.domain.comment.dto.CommentDTO;
import com.laybysystem.domain.like.dto.LikeDTO;
import com.laybysystem.domain.like.service.LikeService;
import com.laybysystem.domain.post.dto.PostDTO;
import com.laybysystem.domain.user.dto.UserDTO;
import com.laybysystem.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeControllerImpl implements LikeController{

    private final LikeService likeService;
    private final JwtProvider jwtProvider;

    @Override
    @PostMapping("/post")
    public ResponseEntity<String> createPostLikeSet(@RequestParam String token, @RequestBody PostDTO post){
        UserDTO user = jwtProvider.getUserInfo(token);
        if(user==null){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(likeService.createPostLikeSet(user.getUserSeq(),post)){
                return ResponseEntity.ok("게시글을 좋아합니다.");
            } else {
                return ResponseEntity.badRequest().body("좋아요에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    @PostMapping("/comment")
    public ResponseEntity<String> createCommentLikeSet(@RequestParam String token, @RequestBody CommentDTO comment){
        UserDTO user = jwtProvider.getUserInfo(token);
        if(user==null){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(likeService.createCommentLikeSet(user.getUserSeq(),comment)){
                return ResponseEntity.ok("댓글을 좋아합니다.");
            } else {
                return ResponseEntity.badRequest().body("좋아요에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    @DeleteMapping("/post")
    public ResponseEntity<String> deletePostLikeSet(@RequestParam String token, @RequestBody PostDTO post){
        UserDTO user = jwtProvider.getUserInfo(token);
        if(user==null){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(likeService.deletePostLikeSet(user.getUserSeq(),post)){
                return ResponseEntity.ok("좋아요를 취소했습니다.");
            } else {
                return ResponseEntity.badRequest().body("좋아요취소에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    @DeleteMapping("/comment")
    public ResponseEntity<String> deleteCommentLikeSet(@RequestParam String token, @RequestBody CommentDTO comment){
        UserDTO user = jwtProvider.getUserInfo(token);
        if(user==null){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(likeService.deleteCommentLikeSet(user.getUserSeq(),comment)){
                return ResponseEntity.ok("좋아요를 취소했습니다.");
            } else {
                return ResponseEntity.badRequest().body("좋아요취소에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }
}
