package com.hh99.activeservice.like.service;

public interface LikeService {
    Boolean commonChangeLikeSet(int likeCreatorSeq,int contentType,int contentSeq,int contentProvider,int changeType);
}
