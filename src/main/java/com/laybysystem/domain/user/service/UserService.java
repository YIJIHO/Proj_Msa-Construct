package com.laybysystem.domain.user.service;

import com.laybysystem.domain.user.dto.UserDTO;

import java.sql.Date;

public interface UserService {
    String selectUserIdByUser(String userName,Date userBirth);
    Boolean createUser(UserDTO user);
    UserDTO selectUserBylogin(UserDTO user);
    Boolean changePasswordByUser(UserDTO user);
    Boolean setUserComment(UserDTO user);
    Boolean deleteUserByUser(UserDTO user);
    Boolean checkUserAuth(UserDTO user);
    int fillUser(UserDTO user);
}
