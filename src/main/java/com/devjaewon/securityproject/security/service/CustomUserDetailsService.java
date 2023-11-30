package com.devjaewon.securityproject.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.devjaewon.securityproject.entity.AccountEntity;
import com.devjaewon.securityproject.repository.AccountRopository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private AccountRopository accountRopository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        System.out.println("-----------------custom Detail filter 호출 완료");

        AccountEntity account = accountRopository.findByEmail(email);

        if(account == null){
            throw new UsernameNotFoundException("유저가 없습니다");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER"));

        AccountContext accountContext = new AccountContext(account, roles);

        return accountContext;
    }
}
