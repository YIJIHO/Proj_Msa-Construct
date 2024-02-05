package com.hh99.userservice.user.dto;


import lombok.*;

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
