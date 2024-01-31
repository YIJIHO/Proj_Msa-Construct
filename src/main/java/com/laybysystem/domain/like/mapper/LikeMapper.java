package com.laybysystem.domain.like.mapper;

import com.laybysystem.domain.like.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {
    LikeDTO changeLike(LikeDTO like);
}
