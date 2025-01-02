package com.drivesoft.idms.service;

import com.drivesoft.idms.repository.account.AccountEntity;

import java.util.List;

public interface IDMSService {

    List<AccountEntity> getAccountList();
    void saveOrUpdate(AccountEntity accountEntity);
}
