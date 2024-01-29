package com.laybysystem.domain.post.service;

import com.laybysystem.domain.post.dto.PostDTO;

public interface PostService {
    Boolean createPost(int postCreatorSeq,String postContent);
    PostDTO searchPost(int postSeq);
    Boolean modifyPost(PostDTO post,int postCreatorSeq);
    Boolean deletePost(int postSeq,int postCreatorSeq);
}
