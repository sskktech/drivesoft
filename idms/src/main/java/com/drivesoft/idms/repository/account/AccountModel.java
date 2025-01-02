package com.drivesoft.idms.repository.account;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "accounts")
public class AccountModel {

    @Id
    @Column(name="AccountNumber")
    Integer AccountID;

    @Column
    String AccountStatus;

    @Transient
    private String AccountStatusDesc;
    Integer LoanNumber;


    String FirstName;
    String LastName;
    String ContractSalesPrice;
    String AccType;
    String SalesGroupPerson1ID;
    String ContractDate;
    String CollateralStockNumber;
    String CollateralYearModel;
    String CollateralMake;
    String CollateralModel;
    String Borrower1FirstName;
    String Borrower1LastName;

}
