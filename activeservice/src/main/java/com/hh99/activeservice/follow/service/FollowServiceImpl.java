package com.hh99.activeservice.follow.service;

import com.hh99.activeservice.follow.dto.FollowDTO;
import com.hh99.activeservice.follow.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{
    private final FollowMapper followMapper;

    @Override
    public Boolean createFollowship(int followerSeq,int followingSeq){
        FollowDTO follow = new FollowDTO(followerSeq,followingSeq);
        int createCheck = followMapper.insertFollowship(follow);
        if(createCheck==1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<FollowDTO> searchFollowship(int followerSeq){
        FollowDTO follow = new FollowDTO();
        follow.setFollowerUserSeq(followerSeq);
        List<FollowDTO> followList = followMapper.selectFollowshipByUserSeq(follow);
        if(followList!=null){
            return followList;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteFollowship(int followerSeq,int followingSeq){
        FollowDTO follow = new FollowDTO(followerSeq,followingSeq);
        int createCheck = followMapper.deleteFollowship(follow);
        if(createCheck==1){
            return true;
        } else {
            return false;
        }
    }
}
