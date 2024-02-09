package com.hh99.activeservice.like.mapper;


import com.hh99.activeservice.like.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {
    LikeDTO changeLike(LikeDTO like);
}
