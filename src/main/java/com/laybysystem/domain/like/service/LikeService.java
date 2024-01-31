package com.laybysystem.domain.like.service;

import com.laybysystem.domain.comment.dto.CommentDTO;
import com.laybysystem.domain.post.dto.PostDTO;

public interface LikeService {
    Boolean commonChangeLikeSet(int likeCreatorSeq,int contentType,int contentSeq,int contentProvider,int changeType);
}
