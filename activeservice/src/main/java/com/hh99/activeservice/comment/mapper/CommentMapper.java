package com.hh99.activeservice.comment.mapper;

import com.hh99.activeservice.comment.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insertComment(CommentDTO comment);
    List<CommentDTO> selectCommentListByPostSeq(int postSeq);
    int updateComment(CommentDTO comment);
    int deleteComment(CommentDTO comment);
}
