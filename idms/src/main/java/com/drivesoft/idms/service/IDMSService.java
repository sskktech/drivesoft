package com.drivesoft.idms.service;

import com.drivesoft.idms.repository.account.AccountModel;

import java.util.List;

public interface IDMSService {

    List<AccountModel> getAccountList();
    AccountModel getAccount(AccountModel accountEntity);
    void saveOrUpdate(AccountModel accountEntity);
}
