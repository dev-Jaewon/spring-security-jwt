package com.devjaewon.securityproject.service;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

import com.devjaewon.securityproject.dto.LoginDto;
import com.devjaewon.securityproject.exceptions.exception.ConflictException;

public interface AccountService {
    
    public ResponseEntity<String> create(LoginDto loginData) throws ConflictException;

    public String login(LoginDto loginData) throws BadRequestException;
}
