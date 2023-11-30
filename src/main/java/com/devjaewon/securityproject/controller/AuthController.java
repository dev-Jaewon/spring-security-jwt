package com.devjaewon.securityproject.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjaewon.securityproject.dto.LoginDto;
import com.devjaewon.securityproject.exceptions.exception.ConflictException;
import com.devjaewon.securityproject.service.AccountService;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "create")
    public ResponseEntity<String> createAccount(@RequestBody LoginDto loginData)throws ConflictException {
        return accountService.create(loginData);
    }

    @PostMapping(value = "login")
    public String login(@RequestBody LoginDto loginData) throws BadRequestException {
        return accountService.login(loginData);
    }

    @GetMapping(value = "user")
    public String test() {
        return "user Controller 테스트 완료";
    }

    @GetMapping(value = "admin")
    public String test2() {
        return "admin Controller 테스트 완료";
    }
}
