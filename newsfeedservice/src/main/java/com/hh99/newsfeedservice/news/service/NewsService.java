package com.hh99.newsfeedservice.news.service;

import java.util.List;

public interface NewsService {
    Boolean createNewsLog(int newsLogCreator,int newsContentProvider,int newsLogType);
    public List<String> searchNewsfeed(int userSeq);
}
