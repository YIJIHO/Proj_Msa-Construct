package com.hh99.activeservice.comment.service;

import com.hh99.activeservice.comment.dto.CommentDTO;
import java.util.List;

public interface CommentService {
    Boolean createComment(CommentDTO comment, int commentCreatorSeq);
    List<CommentDTO> searchCommentList(int postSeq);
    Boolean modifyComment(CommentDTO comment,int commentCreatorSeq);
    Boolean deleteComment(int commentSeq,int commentCreatorSeq);
}
