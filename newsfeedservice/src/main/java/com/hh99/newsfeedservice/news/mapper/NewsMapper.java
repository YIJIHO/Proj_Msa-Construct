package com.hh99.newsfeedservice.news.mapper;


import com.hh99.newsfeedservice.news.dto.NewsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    int insertNewsLog(NewsDTO news);
    List<NewsDTO> selectNewsByUserSeq(int userSeq);
}
