package com.laybysystem.domain.follow.service;

import com.laybysystem.domain.follow.dto.FollowDTO;
import com.laybysystem.domain.follow.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{
    private final FollowMapper followMapper;

    @Override
    public Boolean createFollowship(FollowDTO follow){
        int createCheck = followMapper.insertFollowship(follow);
        if(createCheck==1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<FollowDTO> searchFollowship(FollowDTO follow){
        List<FollowDTO> followList = followMapper.selectFollowshipByUserSeq(follow);
        if(followList!=null){
            return followList;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteFollowship(FollowDTO follow){
        int createCheck = followMapper.deleteFollowship(follow);
        if(createCheck==1){
            return true;
        } else {
            return false;
        }
    }
}
