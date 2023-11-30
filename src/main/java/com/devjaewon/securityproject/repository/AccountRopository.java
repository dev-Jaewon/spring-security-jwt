package com.devjaewon.securityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devjaewon.securityproject.entity.AccountEntity;

@Repository
public interface AccountRopository extends JpaRepository<AccountEntity, Long> {
    public AccountEntity findByEmail(String email);
}
