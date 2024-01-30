package com.laybysystem.domain.comment.mapper;

import com.laybysystem.domain.comment.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insertComment(CommentDTO comment);
    List<CommentDTO> selectCommentListByPostSeq(int postSeq);
    int updateComment(CommentDTO comment);
    int deleteComment(CommentDTO comment);
}
