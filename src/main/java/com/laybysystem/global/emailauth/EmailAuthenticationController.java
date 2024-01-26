package com.laybysystem.global.emailauth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email-auth")
public class EmailAuthenticationController {
//    private final EmailAuthenticationService emailAuthenticationService;
//    @ResponseBody
//    @PostMapping("/req")
//    public ResponseEntity<String> emailAuth(@RequestParam String requestEmail) throws MessagingException, UnsupportedEncodingException  {
//        System.out.println("요청");
//        String authCode = emailAuthenticationService.sendEmail(requestEmail);
//        return ResponseEntity.ok(authCode);
//    }

}