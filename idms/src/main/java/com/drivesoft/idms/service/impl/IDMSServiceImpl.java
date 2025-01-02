package com.drivesoft.idms.service.impl;

import com.drivesoft.idms.dao.AccountDAO;
import com.drivesoft.idms.repository.account.AccountModel;
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
    public List<AccountModel> getAccountList() {
        List<AccountModel> accountList =  new ArrayList<AccountModel>();
        accountDAO.findAll().iterator().forEachRemaining(accountList::add);
       return accountList;
    }

    @Override
    public AccountModel getAccount(AccountModel accountEntity) {
        return accountDAO.findByAccountID(accountEntity.getAccountID());
    }

    public void saveOrUpdate(AccountModel accountEntity){
        accountDAO.save(accountEntity);
    }
}
