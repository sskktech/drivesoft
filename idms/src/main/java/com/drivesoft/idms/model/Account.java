package com.drivesoft.idms.model;

import lombok.Data;

@Data
public class Account {

    private Integer AccountID;
    private Integer institutionId;
    private Boolean includeAccountSummary;
    private Boolean includeBalances;
    private Integer paymentFormId;
    private Integer deliveryMethodId;
    private String FirstName;
    private String LastName;
    private String ContractSalesPrice;
    private String AccType;
    private String SalesGroupPerson1ID;
    private String ContractDate;
    private String CollateralStockNumber;
    private String CollateralYearModel;
    private String CollateralMake;
    private String CollateralModel;
    private String Borrower1FirstName;
    private String Borrower1LastName;

}

