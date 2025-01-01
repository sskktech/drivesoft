package com.drivesoft.idms.model;

import lombok.Data;

import java.util.EnumMap;

@Data
public class Account {

    private Integer accountNumber;
    private Integer institutionId;
    private Boolean includeAccountSummary;
    private Boolean includeBalances;
    private Integer paymentFormId;
    private Integer deliveryMethodId;




}

