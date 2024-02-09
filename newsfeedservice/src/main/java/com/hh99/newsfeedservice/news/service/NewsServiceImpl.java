package com.hh99.newsfeedservice.news.service;

import com.hh99.newsfeedservice.news.dto.NewsDTO;
import com.hh99.newsfeedservice.news.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<String> searchNewsfeed(int userSeq){
        List<NewsDTO> outNewsLog = newsMapper.selectNewsByUserSeq(userSeq);
        List<String> newsFeed = new ArrayList<>();
        for (NewsDTO newsLog : outNewsLog) {
            newsFeed.add(newsLog.getNewsLogMessage());
        }
        return newsFeed;
    }
}
