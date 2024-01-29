package com.laybysystem.domain.user.mapper;

import com.laybysystem.domain.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(UserDTO user);
    int selectAuthcodeByUser(UserDTO userr);
    int updateUser(UserDTO user);
    UserDTO selectUserBylogin(UserDTO user);
    String selectUserIdByUser(UserDTO user);
    int updatePasswordByUser(UserDTO user);
    int updateImageByUser(UserDTO user);
    int updateCommentByUser(UserDTO user);
    int deleteUserByUser(UserDTO user);

}
