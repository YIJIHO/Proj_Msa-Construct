package com.laybysystem.domain.like.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDTO {
    private int likeSeq;
    private int likeCreatorSeq;
    private int contentType;
    private int contentSeq;
    private int contentProvider;
}
