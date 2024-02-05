package com.hh99.userservice.user.service;


import com.hh99.userservice.user.dto.UserDTO;
import com.hh99.userservice.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public Boolean createUser(UserDTO user){
        int ck = userMapper.insertUser(user);
        if(ck==1){
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Boolean checkUserAuth(UserDTO user){
        int checkUser = userMapper.selectAuthcodeByUser(user);
        if(checkUser==1){
            return true;
        } else {
            return false;
        }
    }
    @Override
    public int fillUser(UserDTO user){
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        return userMapper.updateUser(user);
    }
    @Override
    public UserDTO selectUserBylogin(UserDTO user){
        UserDTO outputUser = userMapper.selectUserBylogin(user);
        if (outputUser == null || !passwordEncoder.matches(user.getUserPw(), outputUser.getUserPw())) {
            return null;
        } else {
            return outputUser;
        }
    }
    @Override
    public String selectUserIdByUser(String userName,Date userBirth) {
        UserDTO user = new UserDTO();
        user.setUserName(userName);
        user.setUserBirth(userBirth);
        String userId = userMapper.selectUserIdByUser(user);
        if(userId==null){
            return null;
        } else {
            return userId;
        }
    }
    @Override
    public Boolean changePasswordByUser(UserDTO user){
        System.out.println("진입2");
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        int complete = userMapper.updatePasswordByUser(user);
        if(complete==1){
            System.out.println("진입3");
            return true;
        } else {
            System.out.println("진입4");
            return false;
        }
    }
    @Override
    public Boolean setUserImage(UserDTO user){
        int complete = userMapper.updateImageByUser(user);
        if(complete==1){
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Boolean setUserComment(UserDTO user){
        int complete = userMapper.updateCommentByUser(user);
        if(complete == 1){
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Boolean deleteUserByUser(UserDTO user){
        if(selectUserBylogin(user)!=null){
            int complete = userMapper.deleteUserByUser(user);
            if(complete == 1){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
