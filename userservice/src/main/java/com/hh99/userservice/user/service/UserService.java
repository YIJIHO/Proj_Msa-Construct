package com.hh99.userservice.user.service;

import com.hh99.userservice.user.dto.UserDTO;

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
