package com.hh99.activeservice.follow.service;

import com.hh99.activeservice.follow.dto.FollowDTO;
import java.util.List;

public interface FollowService {
    Boolean createFollowship(int followerSeq,int followingSeq);
    List<FollowDTO> searchFollowship(int followerSeq);
    Boolean deleteFollowship(int followerSeq,int followingSeq);
}
