package com.hh99.activeservice.like.controller;


import com.hh99.activeservice.common.ApiRequestService;
import com.hh99.activeservice.comment.dto.CommentDTO;
import com.hh99.activeservice.like.service.LikeService;
import com.hh99.activeservice.post.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeControllerImpl implements LikeController{

    private final LikeService likeService;
    private final ApiRequestService apiRequestService;

    @Override
    @PostMapping("/post")
    public ResponseEntity<String> createPostLikeSet(@RequestParam String token, @RequestBody PostDTO post){
        System.out.println("진입1");
        int userSeq = apiRequestService.getUserSeq(token);
        if(userSeq==0){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(likeService.commonChangeLikeSet(userSeq,1,post.getPostSeq(),post.getPostCreatorSeq(),1)){
                apiRequestService.createNewsLog(userSeq,post.getPostCreatorSeq(),1);
                return ResponseEntity.ok("게시글을 좋아합니다.");
            } else {
                return ResponseEntity.badRequest().body("좋아요에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    @DeleteMapping("/post")
    public ResponseEntity<String> deletePostLikeSet(@RequestParam String token, @RequestBody PostDTO post) {
        int userSeq = apiRequestService.getUserSeq(token);
        if (userSeq == 0) {
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if (likeService.commonChangeLikeSet(userSeq, 1, post.getPostSeq(), post.getPostCreatorSeq(), 2)) {
                return ResponseEntity.ok("게시글좋아요를 취소했습니다.");
            } else {
                return ResponseEntity.badRequest().body("좋아요취소에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    @PostMapping("/comment")
    public ResponseEntity<String> createCommentLikeSet(@RequestParam String token, @RequestBody CommentDTO comment){
        int userSeq = apiRequestService.getUserSeq(token);
        if (userSeq == 0) {
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(likeService.commonChangeLikeSet(userSeq,2,comment.getCommentSeq(),comment.getCommentCreatorSeq(),1)){
                apiRequestService.createNewsLog(userSeq,comment.getCommentCreatorSeq(),3);
                return ResponseEntity.ok("댓글을 좋아합니다.");
            } else {
                return ResponseEntity.badRequest().body("좋아요에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }
    
    @Override
    @DeleteMapping("/comment")
    public ResponseEntity<String> deleteCommentLikeSet(@RequestParam String token, @RequestBody CommentDTO comment){
        int userSeq = apiRequestService.getUserSeq(token);
        if (userSeq == 0) {
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(likeService.commonChangeLikeSet(userSeq,2,comment.getCommentSeq(), comment.getCommentCreatorSeq(),2)){
                return ResponseEntity.ok("댓글좋아요를 취소했습니다.");
            } else {
                return ResponseEntity.badRequest().body("좋아요취소에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }
}
