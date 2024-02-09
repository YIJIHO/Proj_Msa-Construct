package com.hh99.activeservice.post.service;


import com.hh99.activeservice.post.dto.PostDTO;

public interface PostService {
    Boolean createPost(int postCreatorSeq,String postContent);
    PostDTO searchPost(int postSeq);
    Boolean modifyPost(PostDTO post,int postCreatorSeq);
    Boolean deletePost(int postSeq,int postCreatorSeq);
}
