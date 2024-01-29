package com.laybysystem.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
    private int postSeq;
    private int postCreatorSeq;
    private String postContent;
    private Date postCreationDate;
}
