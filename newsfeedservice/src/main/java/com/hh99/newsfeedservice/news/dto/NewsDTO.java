package com.hh99.newsfeedservice.news.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDTO {
    private int newsLogSeq;
    private int newsLogCreator;
    private int newsContentProvider;
    private String newsLogMessage;
    private int newsLogType;
    private Timestamp newsLogCreateTime;

}
