package com.drivesoft.idms.dao;

import com.drivesoft.idms.repository.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountDAO extends JpaRepository<AccountEntity, Integer> {
    @Override
    List<AccountEntity> findAll();

    @Override
    AccountEntity save(AccountEntity entity);
}
