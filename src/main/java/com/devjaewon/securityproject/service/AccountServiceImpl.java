package com.devjaewon.securityproject.service;

import java.util.Arrays;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devjaewon.securityproject.dto.LoginDto;
import com.devjaewon.securityproject.entity.AccountEntity;
import com.devjaewon.securityproject.exceptions.exception.ConflictException;
import com.devjaewon.securityproject.repository.AccountRopository;
import com.devjaewon.securityproject.security.JwtProvider;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRopository accountRopository;

    @Override
    public ResponseEntity<String> create(LoginDto loginData) throws ConflictException {
        try {
            accountRopository
                    .save(new AccountEntity(loginData.getEmail(), passwordEncoder.encode(loginData.getPassword())));

            return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
        } catch (Exception e) {
            throw new ConflictException("email을 확인해주세요.");
        }
    }

    @Override
    public String login(LoginDto loginData) throws BadRequestException {

        AccountEntity resultUser = accountRopository.findByEmail(loginData.getEmail());

        if (resultUser != null && passwordEncoder.matches(loginData.getPassword(), resultUser.getPassword())) {
            JwtProvider jwtProvider = new JwtProvider();

            return jwtProvider.createAccessToken(resultUser.getEmail(), Arrays.asList("USER"));
        } else {
            throw new BadRequestException("입력이 잘 못 되었습니다.");
        }
    }
}
