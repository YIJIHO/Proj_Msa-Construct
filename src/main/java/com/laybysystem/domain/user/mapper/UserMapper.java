package com.laybysystem.domain.user.mapper;

import com.laybysystem.domain.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    String selectUserIdByUser(UserDTO user);
    int insertUser(UserDTO user);
    UserDTO selectUserBylogin(UserDTO user);
    int updatePasswordByUser(UserDTO user);
    int updateCommentByUser(UserDTO user);
    int deleteUserByUser(UserDTO user);
}
