package com.laybysystem.domain.post.controller;

import com.laybysystem.domain.news.service.NewsService;
import com.laybysystem.domain.post.dto.PostDTO;
import com.laybysystem.domain.post.service.PostService;
import com.laybysystem.domain.user.dto.UserDTO;
import com.laybysystem.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostControllerImpl implements PostController{
    private final PostService postService;
    private final JwtProvider jwtProvider;
    private final NewsService newsService;
    @Override
    @PostMapping
    public ResponseEntity<String> createPost(@RequestParam String postContent, @RequestParam String token) {
        UserDTO user = jwtProvider.getUserInfo(token);
        if(user==null){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(postService.createPost(user.getUserSeq(),postContent)){
                newsService.createNewsLog(user.getUserSeq(),0,0);
                return ResponseEntity.ok("게시글이 작성되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("게시글 작성에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<String> searchPost(@RequestParam int postSeq) {
        PostDTO post = postService.searchPost(postSeq);
        if(post==null){
            return ResponseEntity.badRequest().body("게시글 찾을 수 없습니다.");
        } else {
            return ResponseEntity.ok(post.getPostContent());
        }
    }

    @Override
    @PatchMapping
    public ResponseEntity<String> modifyPost(@RequestBody PostDTO post, @RequestParam String token) {
        UserDTO user = jwtProvider.getUserInfo(token);
        if(user==null){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(postService.modifyPost(post,user.getUserSeq())){
                return ResponseEntity.ok("게시글이 수정되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("게시글 수정에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    @DeleteMapping
    public ResponseEntity<String> deletePost(@RequestParam int postSeq, @RequestParam String token) {
        UserDTO user = jwtProvider.getUserInfo(token);
        if(user==null){
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            if(postService.deletePost(postSeq,user.getUserSeq())){
                return ResponseEntity.ok("게시글이 삭제되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("게시글 삭제에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }
}
