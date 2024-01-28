package com.laybysystem.domain.user.controller;

import com.laybysystem.domain.user.dto.UserDTO;
import com.laybysystem.domain.user.service.UserService;
import com.laybysystem.global.emailauth.EmailAuthenticationService;
import com.laybysystem.global.security.JwtProvider;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
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
    private final JwtProvider jwtProvider;
    private final EmailAuthenticationService emailAuthenticationService;

    @PostMapping("/email-auth")
    public ResponseEntity<String> emailAuth(@RequestParam String requestEmail) throws MessagingException, UnsupportedEncodingException {
        String authCode = emailAuthenticationService.sendEmail(requestEmail);
        if(authCode==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("인증요청에 실패하였습니다. 다시 시도해주세요.");
        } else {
            UserDTO user = new UserDTO();
            user.setUserEmail(requestEmail);
            user.setUserAuthcode(authCode);
            userService.createUser(user);
            return ResponseEntity.ok(authCode);
        }
    }

    @PostMapping("/email-auth-checking")
    public ResponseEntity<String> emailAuthChecking(@RequestParam String userEmail, @RequestParam String inputAuthenticationCode){
        UserDTO user = new UserDTO();
        user.setUserEmail(userEmail);
        user.setUserAuthcode(inputAuthenticationCode);
        if(userService.checkUserAuth(user)){
            return ResponseEntity.ok().body("인증이 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("인증코드가 올바르지 않습니다.");
        }
    }

    //FIN = user001
    @PostMapping()
    public ResponseEntity<String> createAccount(@RequestBody UserDTO user) {//*필수 : userName, userBirth, userId, userPw (관리자일 경우 userType = 0)
        int createuser = userService.fillUser(user);
        if(createuser==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이메일 인증을 완료하지 않았습니다.");
        } else if(createuser==1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원가입에 실패하였습니다. 다시 시도해주세요.");
        } else {
            return ResponseEntity.ok().body("회원가입이 완료되었습니다. 로그인해주세요.");
        }
    }

    //FIN = user002
    @GetMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody UserDTO user) {//userId, userPw
        //우선확인을 위해 boolean타입으로 처리
        //UserDTO로 받아와서 JWT처리
        UserDTO loginUser = userService.selectUserBylogin(user);
        if(loginUser==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디 또는 비밀번호가 일치하지않습니다.");
        } else {
            String token = jwtProvider.createToken(loginUser);
            return ResponseEntity.ok().body("로그인 되었습니다."+token);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String token){
        String logoutToken = jwtProvider.destroyToken(token);
        return ResponseEntity.ok().body("토큰정보 : "+logoutToken);
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
    public ResponseEntity<String> findPw(@RequestBody UserDTO user) {
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
    public ResponseEntity<String> putUserComment(@RequestParam String token,@RequestParam String comment) {
        UserDTO user = jwtProvider.getUserInfo(token);
        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            user.setUserComment(comment);
            if(userService.setUserComment(user)){
                return ResponseEntity.ok().body("코멘트 설정이 완료되었습니다."+user.getUserSeq()+"/"+user.getUserEmail()+"/"+user.getUserComment());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("코멘트설정에 실패하였습니다. 다시 시도해주세요.");
            }
        }
    }

    //FIN = user007
    @DeleteMapping()
    public ResponseEntity<String> deleteAccount(@RequestParam String token, @RequestParam String password) {//userId, userPw
        UserDTO user = jwtProvider.getUserInfo(token);
        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("토큰이 만료되었습니다. 다시 로그인해주세요.");
        } else {
            user.setUserPw(password);
            if(userService.deleteUserByUser(user)){
                return ResponseEntity.ok().body("계정 삭제가 완료되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("계정삭제에 실패하였습니다. 다시 시도해주세요.");

            }
         }
    }
}
