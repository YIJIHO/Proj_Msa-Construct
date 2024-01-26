package com.laybysystem.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

public interface UserController {

    ResponseEntity<String> findId(@RequestParam String userName, @RequestParam Date userBirth);
}
