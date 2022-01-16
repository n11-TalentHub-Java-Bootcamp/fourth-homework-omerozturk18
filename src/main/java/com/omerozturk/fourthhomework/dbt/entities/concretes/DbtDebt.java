package com.omerozturk.fourthhomework.dbt.entities.concretes;

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
@Table(name = "DBT_DEBT")
@Data
public class DbtDebt implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String explanation;
    private Date expiryDate;
    private BigDecimal mainDebt;
    private BigDecimal debtAmount;
    private EnumDbtDebtType debtType;
    private Long usrUserId;
    private Long topDebtId;
}
