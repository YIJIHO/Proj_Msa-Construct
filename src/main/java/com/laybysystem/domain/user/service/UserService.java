package com.laybysystem.domain.user.service;

import com.laybysystem.domain.user.dto.UserDTO;

import java.sql.Date;

public interface UserService {
    Boolean createUser(UserDTO user);
    Boolean checkUserAuth(UserDTO user);
    int fillUser(UserDTO user);
    UserDTO selectUserBylogin(UserDTO user);
    String selectUserIdByUser(String userName,Date userBirth);
    Boolean changePasswordByUser(UserDTO user);
    Boolean setUserImage(UserDTO user);
    Boolean setUserComment(UserDTO user);
    Boolean deleteUserByUser(UserDTO user);

}
