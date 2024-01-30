package com.laybysystem.domain.like.service;

import com.laybysystem.domain.comment.dto.CommentDTO;
import com.laybysystem.domain.post.dto.PostDTO;

public interface LikeService {
    Boolean createPostLikeSet(int likeCreatorSeq, PostDTO post);
    Boolean createCommentLikeSet(int likeCreatorSeq, CommentDTO comment);
    Boolean deletePostLikeSet(int likeCreatorSeq, PostDTO post);
    Boolean deleteCommentLikeSet(int likeCreatorSeq, CommentDTO comment);
}
