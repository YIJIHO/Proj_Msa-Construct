package com.laybysystem.domain.user.service;

import com.laybysystem.domain.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.laybysystem.domain.user.mapper.UserMapper;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    public Boolean createUser(UserDTO user){
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        int ck = userMapper.insertUser(user);
        if(ck==1){
            return true;
        } else {
            return false;
        }
    }
    public Boolean selectUserBylogin(UserDTO user){
        UserDTO outputUser = userMapper.selectUserBylogin(user);
        if (outputUser == null || passwordEncoder.matches(user.getUserPw(), outputUser.getUserPw())) {
            return false;
        } else {
            return true;
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
}
