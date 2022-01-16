package com.omerozturk.fourthhomework.pym.dataAccess.abstracts;

import com.omerozturk.fourthhomework.dbt.entities.enums.EnumDbtDebtType;
import com.omerozturk.fourthhomework.pym.entities.concretes.PymPayment;
import com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PymPaymentDao extends JpaRepository<PymPayment,Long> {
    List<PymPayment> findAllByPaymentDateBetween(Date paymentDateStart, Date paymentDateEnd);
    List<PymPayment> findAllByUsrUserId(Long id);
    @Query("select pymPayment"+
            " from PymPayment pymPayment" +
            " left join DbtDebt dbtDebt  on   pymPayment.dbtDebtId= dbtDebt.id " +
            " where pymPayment.usrUserId = :usrUserId and dbtDebt.debtType = :debtType")
    List<PymPayment> findAllPaidLateFeeAmountByUser(EnumDbtDebtType debtType, Long usrUserId);
/*@Query("select  new com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentDto (" +
            " pymPayment.id," +
            " pymPayment., " +
            " pymPayment.categoryName," +
            " pymPayment.price," +
            " pymPayment.firstName," +
            " pymPayment.lastName" +
            " )" +
            " from PymPayment pymPayment" +
            " left join DbtDebt dbtDebt  on   pymPayment.dbtDebtId= dbtDebt.id " +
            " where pymPayment.usrUserId = :usrUserId and dbtDebt.debtType = :debtType")*/
}
