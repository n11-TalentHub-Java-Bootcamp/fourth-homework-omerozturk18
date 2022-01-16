package com.omerozturk.fourthhomework.pym.services.entityservice;


import com.omerozturk.fourthhomework.dbt.entities.enums.EnumDbtDebtType;
import com.omerozturk.fourthhomework.gen.utilities.service.BaseEntityService;
import com.omerozturk.fourthhomework.pym.dataAccess.abstracts.PymPaymentDao;
import com.omerozturk.fourthhomework.pym.entities.concretes.PymPayment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PymPaymentEntityService extends BaseEntityService<PymPayment, PymPaymentDao> {
    public PymPaymentEntityService(PymPaymentDao dao) {
        super(dao);
    }

    public List<PymPayment> getByDateRange(Date startDate, Date endDate) {
        List<PymPayment> pymPaymentList = getDao().findAllByPaymentDateBetween(startDate,endDate);
        return pymPaymentList;
    }

    public List<PymPayment> findByUserId(Long id) {
        List<PymPayment> pymPaymentList = getDao().findAllByUsrUserId(id);
        return pymPaymentList;
    }
    public BigDecimal findTotalPaidLateFeeAmountByUser(Long id) {
        List<PymPayment> pymPaymentList = getDao().findAllPaidLateFeeAmountByUser(EnumDbtDebtType.DELAY_HIKE,id);
        BigDecimal totalPaidLateFeeAmount = BigDecimal.ZERO;
        totalPaidLateFeeAmount = pymPaymentList.stream()
                .map(p -> p.getPaymentAmount().multiply(p.getPaymentAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPaidLateFeeAmount;
    }
    public List<PymPayment> findPaidLateFeesByUser(Long id) {
        List<PymPayment> pymPaymentList = getDao().findAllPaidLateFeeAmountByUser(EnumDbtDebtType.DELAY_HIKE,id);
        return pymPaymentList;
    }
}
