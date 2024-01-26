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
        if (outputUser == null || !passwordEncoder.matches(user.getUserPw(), outputUser.getUserPw())) {
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
    public Boolean changePasswordByUser(UserDTO user){
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        int complete = userMapper.updatePasswordByUser(user);
        if(complete==1){
            return true;
        } else {
            return false;
        }
    }
    public Boolean setUserComment(UserDTO user){
        //이후에 jwt를 사용해서 인증이 이미 되었다면 userSeq로 바로 수정할 수 있도록
        if(selectUserBylogin(user)){
            int complete = userMapper.updateCommentByUser(user);
            if(complete == 1){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public Boolean deleteUserByUser(UserDTO user){
        //이것도 jwt가 있으면 해당 seq찾아서 바로 삭제할건지? or 비밀번호 검증 한번 더 할건지
        if(selectUserBylogin(user)){
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
