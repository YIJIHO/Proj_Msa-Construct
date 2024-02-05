package com.hh99.userservice.user.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.hh99.userservice.user.dto.UserDTO;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

public interface UserController {
    ResponseEntity<String> emailAuth(@RequestParam String requestEmail) throws MessagingException, UnsupportedEncodingException;
    ResponseEntity<String> emailAuthChecking(@RequestParam String userEmail, @RequestParam String inputAuthenticationCode);
    ResponseEntity<String> createAccount(@RequestBody UserDTO user);
    ResponseEntity<String> logIn(@RequestBody UserDTO user);
    ResponseEntity<String> logout(@RequestParam String token);
    ResponseEntity<String> findId(@RequestParam String userName, @RequestParam Date userBirth);
    ResponseEntity<String> findPw(@RequestBody UserDTO user);
    ResponseEntity<String> putUserImg(@RequestParam String token, @RequestBody MultipartFile file) throws IOException, FirebaseAuthException;
    ResponseEntity<String> putUserComment(@RequestParam String token,@RequestParam String comment);
    ResponseEntity<String> deleteAccount(@RequestParam String token, @RequestParam String password);
}