package com.laybysystem.domain.follow.service;

import com.laybysystem.domain.follow.dto.FollowDTO;

import java.util.List;

public interface FollowService {
    Boolean createFollowship(FollowDTO follow);
    List<FollowDTO> searchFollowship(FollowDTO follow);
    Boolean deleteFollowship(FollowDTO follow);
}
