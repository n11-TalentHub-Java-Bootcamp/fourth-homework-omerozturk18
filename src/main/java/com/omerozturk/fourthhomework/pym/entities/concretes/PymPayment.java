package com.omerozturk.fourthhomework.pym.entities.concretes;

import com.omerozturk.fourthhomework.dbt.entities.enums.EnumDbtDebtType;
import com.omerozturk.fourthhomework.gen.utilities.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PYM_PAYMENT")
@Data
public class PymPayment implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String explanation;
    private BigDecimal paymentAmount;
    private Date paymentDate;
    private Long dbtDebtId;
    private Long usrUserId;
}
