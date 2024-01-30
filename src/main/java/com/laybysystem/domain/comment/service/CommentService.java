package com.laybysystem.domain.comment.service;

import com.laybysystem.domain.comment.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    Boolean createComment(CommentDTO comment, int commentCreatorSeq);
    List<CommentDTO> searchCommentList(int postSeq);
    Boolean modifyComment(CommentDTO comment,int commentCreatorSeq);
    Boolean deleteComment(int commentSeq,int commentCreatorSeq);
}
