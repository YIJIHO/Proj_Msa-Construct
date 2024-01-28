package com.laybysystem.domain.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private int userSeq;
    private String userName;
    private Date userBirth;
    private String userEmail;
    private String userPw;
    private String userAuthcode;
    private String userImg;
    private String userComment;
    private int userType;
    private int userStatus;
}
