package com.laybysystem.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private int commentSeq;
    private int postSeq;
    private int commentCreatorSeq;
    private String commentContent;
    private Date commentCreationDate;
    private int commentLikeCnt;
    private int likeRequestType;
}
