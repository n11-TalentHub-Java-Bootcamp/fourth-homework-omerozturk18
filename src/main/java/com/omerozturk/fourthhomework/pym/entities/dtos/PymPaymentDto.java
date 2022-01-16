package com.omerozturk.fourthhomework.pym.entities.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PymPaymentDto {
    private Long id;
    private String explanation;
    private BigDecimal paymentAmount;
    private Date paymentDate;
    private Long dbtDebtId;
    private Long usrUserId;
}
