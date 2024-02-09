package com.hh99.activeservice.post.controller;


import com.hh99.activeservice.common.ApiRequestService;
import com.hh99.activeservice.post.dto.PostDTO;
import com.hh99.activeservice.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostControllerImpl implements PostController {
    private final PostService postService;
    private final ApiRequestService apiRequestService;
    @Override
    @PostMapping
    public ResponseEntity<String> createPost(@RequestParam String postContent, @RequestParam String token) {
        int userSeq = apiRequestService.getUserSeq(token);
        if(userSeq==0){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(postService.createPost(userSeq,postContent)){
                apiRequestService.createNewsLog(userSeq,0,0);
                return ResponseEntity.ok("게시글이 작성되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("게시글 작성에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<String> searchPost(@RequestParam int postSeq) {
        System.out.println("게시글찾기 진입");
        PostDTO post = postService.searchPost(postSeq);
        if(post==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글 찾을 수 없습니다.");
        } else {
            return ResponseEntity.ok(post.getPostContent());
        }
    }

    @Override
    @PatchMapping
    public ResponseEntity<String> modifyPost(@RequestBody PostDTO post, @RequestParam String token) {
        int userSeq = apiRequestService.getUserSeq(token);
        if(userSeq==0){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(postService.modifyPost(post,userSeq)){
                return ResponseEntity.ok("게시글이 수정되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("게시글 수정에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    @DeleteMapping
    public ResponseEntity<String> deletePost(@RequestParam int postSeq, @RequestParam String token) {
        int userSeq = apiRequestService.getUserSeq(token);
        if(userSeq==0){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(postService.deletePost(postSeq,userSeq)){
                return ResponseEntity.ok("게시글이 삭제되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("게시글 삭제에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }
}
