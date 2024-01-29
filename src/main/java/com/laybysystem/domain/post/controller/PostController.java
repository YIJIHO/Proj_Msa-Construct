package com.laybysystem.domain.post.controller;

import com.laybysystem.domain.post.dto.PostDTO;
import org.springframework.http.ResponseEntity;

public interface PostController {
    ResponseEntity<String> createPost(String postContent,String token);
    ResponseEntity<String> searchPost(int postSeq);
    ResponseEntity<String> modifyPost(PostDTO post,String token);
    ResponseEntity<String> deletePost(int postSeq,String token);

}
