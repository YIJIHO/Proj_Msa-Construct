package com.laybysystem.domain.follow.mapper;

import com.laybysystem.domain.follow.dto.FollowDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
    int insertFollowship(FollowDTO follow);
    List<FollowDTO> selectFollowshipByUserSeq(FollowDTO follow);
    int deleteFollowship(FollowDTO follow);
}
