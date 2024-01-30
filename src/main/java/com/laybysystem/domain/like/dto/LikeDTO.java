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
    private int likeType;
    private int contentSeq;
    private int changeType;
    private boolean result;
}
