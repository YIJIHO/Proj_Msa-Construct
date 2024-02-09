package com.hh99.activeservice.comment.controller;

import com.hh99.activeservice.comment.dto.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CommentController {
    ResponseEntity<String> createComment(@RequestParam String token, @RequestBody CommentDTO comment);
    ResponseEntity<?> searchCommentList(@RequestParam int postSeq);
    ResponseEntity<String> modifyComment(@RequestParam String token,@RequestBody CommentDTO comment);
    ResponseEntity<String> deleteComment(@RequestParam String token,@RequestParam int commentSeq);
}
