package com.hh99.activeservice.post.controller;

import com.hh99.activeservice.post.dto.PostDTO;
import org.springframework.http.ResponseEntity;

public interface PostController {
    ResponseEntity<String> createPost(String postContent,String token);
    ResponseEntity<String> searchPost(int postSeq);
    ResponseEntity<String> modifyPost(PostDTO post,String token);
    ResponseEntity<String> deletePost(int postSeq,String token);

}
