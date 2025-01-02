package com.drivesoft.idms.dao;

import com.drivesoft.idms.repository.account.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountDAO extends JpaRepository<AccountModel, Integer> {
    @Override
    List<AccountModel> findAll();

    AccountModel getById(AccountModel accountEntity);

    @Override
    AccountModel save(AccountModel entity);
}
