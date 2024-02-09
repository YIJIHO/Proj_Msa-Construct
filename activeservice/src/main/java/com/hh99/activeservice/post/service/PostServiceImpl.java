package com.hh99.activeservice.post.service;


import com.hh99.activeservice.post.dto.PostDTO;
import com.hh99.activeservice.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostMapper postMapper;
    @Override
    public Boolean createPost(int postCreatorSeq,String postContent) {
        PostDTO post = new PostDTO();
        post.setPostCreatorSeq(postCreatorSeq);
        post.setPostContent(postContent);
        int createCheck = postMapper.insertPost(post);
        if(createCheck==1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PostDTO searchPost(int postSeq) {
        PostDTO post = postMapper.selectPostByPostDTO(postSeq);
        if(post==null){
            return null;
        } else {
            return post;
        }
    }

    @Override
    public Boolean modifyPost(PostDTO post,int postCreatorSeq) {
        post.setPostCreatorSeq(postCreatorSeq);
        postMapper.updatePost(post);
        int createCheck = postMapper.updatePost(post);
        if(createCheck==1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean deletePost(int postSeq,int postCreatorSeq) {
        PostDTO post = new PostDTO();
        post.setPostSeq(postSeq);
        post.setPostCreatorSeq(postCreatorSeq);
        if(postMapper.deletePost(post)==1){
            return true;
        } else {
            return false;
        }
    }
}
