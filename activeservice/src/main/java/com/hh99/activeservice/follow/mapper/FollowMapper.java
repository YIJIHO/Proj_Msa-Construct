package com.hh99.activeservice.follow.mapper;

import com.hh99.activeservice.follow.dto.FollowDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
    int insertFollowship(FollowDTO follow);
    List<FollowDTO> selectFollowshipByUserSeq(FollowDTO follow);
    int deleteFollowship(FollowDTO follow);
}
