package com.laybysystem.domain.news.service;

import com.laybysystem.domain.news.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    Boolean createNewsLog(int newsLogCreator,int newsContentProvider,int newsLogType);
    public List<NewsDTO> searchNewsfeed(int userSeq);
}
