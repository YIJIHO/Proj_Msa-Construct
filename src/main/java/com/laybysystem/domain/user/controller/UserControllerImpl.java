package com.laybysystem.domain.user.controller;

import com.laybysystem.domain.user.dto.UserDTO;
import com.laybysystem.domain.user.service.UserService;
import com.laybysystem.global.emailauth.EmailAuthenticationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserControllerImpl implements UserController{
    private final UserService userService;
    private final EmailAuthenticationService emailAuthenticationService;
    private String authenticationCode = "";
    private boolean authStatus = false;

    @PostMapping("/email-auth")
    public ResponseEntity<String> emailAuth(@RequestParam String requestEmail) throws MessagingException, UnsupportedEncodingException {
        String authCode = emailAuthenticationService.sendEmail(requestEmail);
        if(authCode!=null){
            authenticationCode = authCode;
        }
        System.out.println(authenticationCode);
        return ResponseEntity.ok(authCode);
    }

    @PostMapping("/email-auth-checking")
    public ResponseEntity<String> emailAuthChecking(@RequestParam String inputAuthenticationCode){
        if(!inputAuthenticationCode.equals(authenticationCode)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("인증코드가 올바르지 않습니다.");
        } else {
            authStatus = true;
            authenticationCode="";
            return ResponseEntity.ok().body("인증이 완료되었습니다.");
        }
    }

    //FIN = user001
    @PostMapping()
    public ResponseEntity<String> createAccount(@RequestBody UserDTO user) {//*필수 : userName, userBirth, userId, userPw (관리자일 경우 userType = 0)
        if(authenticationCode==null || !authStatus){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이메일 인증을 완료하지 않았습니다.");
        } else {
            System.out.println(user);
            boolean complete = userService.createUser(user);
            if(!complete){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원가입에 실패하였습니다. 다시 시도해주세요.");
            } else {
                return ResponseEntity.ok().body("회원가입이 완료되었습니다. 로그인해주세요.");
            }
        }
    }

    //FIN = user002
    @GetMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody UserDTO user) {//userId, userPw
        //우선확인을 위해 boolean타입으로 처리
        //UserDTO로 받아와서 JWT처리
        boolean login = userService.selectUserBylogin(user);
        if(!login){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디 또는 비밀번호가 일치하지않습니다.");
        } else {
            return ResponseEntity.ok().body("로그인 되었습니다.");
        }
    }

    //FIN = user003
    @Override
    @GetMapping("/find-id")
    public ResponseEntity<String> findId(@RequestParam String userName, @RequestParam Date userBirth) {
        String userId = userService.selectUserIdByUser(userName,userBirth);
        if(userId==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 사용자의 ID를 찾을 수 없습니다.");
        } else {
            return ResponseEntity.ok(userId);
        }
    }

    //FIN = user004
    @PatchMapping("/find-pw")
    public ResponseEntity<String> findPw(@RequestBody UserDTO user) {//userId, userName
        boolean complete  = userService.changePasswordByUser(user);
        if(complete){
            return ResponseEntity.ok().body("비밀번호 변경이 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
    }

    //FIN = user005
    @PutMapping("/set-img")
    public ResponseEntity<UserDTO> putUserImg() {
        //이미지는 뭘로 저장할지 고민좀 해봄
        return null;
    }

    //FIN = user006
    @PutMapping("/set-comment")
    public ResponseEntity<String> putUserComment(@RequestBody UserDTO user) {//userId, userPw,userComment
        boolean complete  = userService.setUserComment(user);
        if(complete){
            return ResponseEntity.ok().body("코멘트 설정이 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("코멘트설정에 실패하였습니다. 다시 시도해주세요.");
        }
    }

    //FIN = user007
    @DeleteMapping()
    public ResponseEntity<String> deleteAccount(@RequestBody UserDTO user) {
        boolean complete = userService.deleteUserByUser(user);
        if(complete){
            return ResponseEntity.ok().body("계정 삭제가 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("계정삭제에 실패하였습니다. 다시 시도해주세요.");
        }
    }
}
