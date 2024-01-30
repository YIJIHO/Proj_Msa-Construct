package com.laybysystem.domain.like.service;


import com.laybysystem.domain.comment.dto.CommentDTO;
import com.laybysystem.domain.like.dto.LikeDTO;
import com.laybysystem.domain.like.mapper.LikeMapper;
import com.laybysystem.domain.post.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    public final LikeMapper likemapper;
    public Boolean createPostLikeSet(int likeCreatorSeq, PostDTO post){
        LikeDTO like = new LikeDTO();
        like.setLikeCreatorSeq(likeCreatorSeq);
        like.setLikeType(1);
        like.setContentSeq(post.getPostSeq());
        like.setChangeType(1);
        if(likemapper.insertLike(like)){
            return true;
        } else {
            return false;
        }
    }
    public Boolean createCommentLikeSet(int likeCreatorSeq, CommentDTO comment){
        LikeDTO like = new LikeDTO();
        like.setLikeCreatorSeq(likeCreatorSeq);
        like.setLikeType(2);
        like.setContentSeq(comment.getCommentSeq());
        like.setChangeType(1);
        if(likemapper.insertLike(like)){
            return true;
        } else {
            return false;
        }
    }
    public Boolean deletePostLikeSet(int likeCreatorSeq, PostDTO post){
        LikeDTO like = new LikeDTO();
        like.setLikeCreatorSeq(likeCreatorSeq);
        like.setLikeType(1);
        like.setContentSeq(post.getPostSeq());
        like.setChangeType(2);
        if(likemapper.deleteLike(like)){
            return true;
        } else {
            return false;
        }
    }
    public Boolean deleteCommentLikeSet(int likeCreatorSeq, CommentDTO comment){
        LikeDTO like = new LikeDTO();
        like.setLikeCreatorSeq(likeCreatorSeq);
        like.setLikeType(2);
        like.setContentSeq(comment.getCommentSeq());
        like.setChangeType(2);
        if(likemapper.deleteLike(like)){
            return true;
        } else {
            return false;
        }
    }
}
