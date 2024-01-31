package com.laybysystem.domain.news.mapper;

import com.laybysystem.domain.news.dto.NewsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    int insertNewsLog(NewsDTO news);
    List<NewsDTO> selectNewsByUserSeq(int userSeq);
}
