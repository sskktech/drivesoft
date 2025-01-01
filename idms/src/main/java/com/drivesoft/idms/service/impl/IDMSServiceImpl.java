package com.drivesoft.idms.service.impl;

import com.drivesoft.idms.dao.AccountDAO;
import com.drivesoft.idms.model.Account;
import com.drivesoft.idms.repository.account.AccountEntity;
import com.drivesoft.idms.service.IDMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IDMSServiceImpl implements IDMSService {

    @Autowired
    AccountDAO accountDAO;


    @Override
    public List<AccountEntity> getAccountList() {
        List<AccountEntity> accountList =  new ArrayList<AccountEntity>();
        Account account = new Account();
        accountDAO.findAll().iterator().forEachRemaining(accountList::add);

       return accountList;
    }
}
