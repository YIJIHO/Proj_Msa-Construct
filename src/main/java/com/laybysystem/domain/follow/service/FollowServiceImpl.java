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
        //List<Integer>로 가져와도 무방할듯하나 일단 DTO로 가져옴
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
