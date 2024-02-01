package com.laybysystem.domain.news.service;


import com.laybysystem.domain.news.dto.NewsDTO;
import com.laybysystem.domain.news.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{
    private final NewsMapper newsMapper;

    @Override
    public Boolean createNewsLog(int newsLogCreator,int newsContentProvider,int newsLogType){
        NewsDTO news = new NewsDTO();
        news.setNewsLogCreator(newsLogCreator);
        news.setNewsContentProvider(newsContentProvider);
        news.setNewsLogType(newsLogType);
        if(newsMapper.insertNewsLog(news)==1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<NewsDTO> searchNewsfeed(int userSeq){
        List<NewsDTO> newsFeed = newsMapper.selectNewsByUserSeq(userSeq);
        return newsFeed;
    }
}
