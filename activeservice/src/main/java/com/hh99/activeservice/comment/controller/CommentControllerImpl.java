package com.hh99.activeservice.comment.controller;


import com.hh99.activeservice.common.ApiRequestService;
import com.hh99.activeservice.comment.dto.CommentDTO;
import com.hh99.activeservice.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentControllerImpl implements CommentController{
    private final CommentService commentService;
    private final ApiRequestService apiRequestService;

    @Override
    @PostMapping
    public ResponseEntity<String> createComment(@RequestParam String token, @RequestBody CommentDTO comment){
        int userSeq = apiRequestService.getUserSeq(token);
        if(userSeq==0){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(commentService.createComment(comment,userSeq)){
                apiRequestService.createNewsLog(userSeq,comment.getCommentCreatorSeq(),2);
                return ResponseEntity.ok("댓글작성이 완료되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("댓글작성에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<?> searchCommentList(@RequestParam int postSeq) {
        List<CommentDTO> commentList = commentService.searchCommentList(postSeq);
        if(commentList==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글이 없습니다.");
        } else {
            return ResponseEntity.ok(commentList);
        }
    }

    @Override
    @PatchMapping
    public ResponseEntity<String> modifyComment(@RequestParam String token,@RequestBody CommentDTO comment){
        int userSeq = apiRequestService.getUserSeq(token);
        if(userSeq==0){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
        if(commentService.modifyComment(comment,userSeq)){
            return ResponseEntity.ok("댓글수정이 완료되었습니다. : ");
        } else {
            return ResponseEntity.badRequest().body("댓글수정에 실패하였습니다. 다시 시도해주세요.");
        }
         }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteComment(@RequestParam String token,@RequestParam int commentSeq){
        int userSeq = apiRequestService.getUserSeq(token);
        if(userSeq==0){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(commentService.deleteComment(commentSeq,userSeq)){
                return ResponseEntity.ok("댓글이 삭제되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("댓글삭제에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }
}
