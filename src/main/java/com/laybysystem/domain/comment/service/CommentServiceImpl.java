package com.laybysystem.domain.comment.service;

import com.laybysystem.domain.comment.dto.CommentDTO;
import com.laybysystem.domain.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentMapper commentMapper;
    @Override
    public Boolean createComment(CommentDTO comment,int commentCreatorSeq){
        comment.setCommentCreatorSeq(commentCreatorSeq);
        if(commentMapper.insertComment(comment)==1){
            return true;
        } else {
            return false;
        }
    }
    public List<CommentDTO> searchCommentList(int postSeq) {
        List<CommentDTO> commentList = commentMapper.selectCommentListByPostSeq(postSeq);
        return commentList;
    }
    public Boolean modifyComment(CommentDTO comment, int commentCreatorSeq){
        comment.setCommentCreatorSeq(commentCreatorSeq);
        if(commentMapper.updateComment(comment)==1){
            return true;
        } else {
            return false;
        }
    }
    public Boolean deleteComment(int commentSeq,int commentCreatorSeq){
        CommentDTO comment = new CommentDTO();
        comment.setCommentSeq(commentSeq);
        comment.setCommentCreatorSeq(commentCreatorSeq);
        if(commentMapper.deleteComment(comment)==1){
            return true;
        } else {
            return false;
        }
    }
}
