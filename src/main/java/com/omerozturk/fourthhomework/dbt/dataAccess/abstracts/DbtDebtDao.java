package com.omerozturk.fourthhomework.dbt.dataAccess.abstracts;

import com.omerozturk.fourthhomework.dbt.entities.concretes.DbtDebt;
import com.omerozturk.fourthhomework.dbt.entities.enums.EnumDbtDebtType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface DbtDebtDao  extends JpaRepository<DbtDebt, Long> {
    List<DbtDebt>  findAllByExpiryDateBetween(Date expiryDateStart, Date expiryDateEnd);
    List<DbtDebt> findAllByUsrUserId(Long usrUserId);
    List<DbtDebt> findAllByExpiryDateBeforeAndDebtAmountGreaterThanAndUsrUserId(Date expiryDateStart ,BigDecimal debtAmount,Long usrUserId);
}
