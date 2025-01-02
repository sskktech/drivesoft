package com.drivesoft.idms.dao;

import com.drivesoft.idms.repository.account.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AccountDAO extends JpaRepository<AccountModel, Integer> {
    @Override
    List<AccountModel> findAll();

    AccountModel findByAccountID(Integer accountID);
    @Override
    AccountModel save(AccountModel entity);
}
