package com.omerozturk.fourthhomework.dbt.entities.dtos;

import com.omerozturk.fourthhomework.dbt.entities.enums.EnumDbtDebtType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DbtDebtDto {

    private Long id;
    private String explanation;
    private Date expiryDate;
    private BigDecimal mainDebt;
    private BigDecimal debtAmount;
    private EnumDbtDebtType debtType;
    private Long usrUserId;
    private Long topDebtId;
}
