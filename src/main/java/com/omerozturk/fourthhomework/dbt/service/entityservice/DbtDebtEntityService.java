package com.omerozturk.fourthhomework.dbt.service.entityservice;

import com.omerozturk.fourthhomework.dbt.dataAccess.abstracts.DbtDebtDao;
import com.omerozturk.fourthhomework.dbt.entities.concretes.DbtDebt;
import com.omerozturk.fourthhomework.gen.utilities.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static java.math.BigDecimal.*;

@Service
public class DbtDebtEntityService extends BaseEntityService<DbtDebt, DbtDebtDao> {
    public DbtDebtEntityService(DbtDebtDao dao) {
        super(dao);
    }

    public List<DbtDebt> getByDateRange(Date startDate, Date endDate) {
        List<DbtDebt> dbtDebtList = getDao().findAllByExpiryDateBetween(startDate,endDate);
        return dbtDebtList;
    }

    public List<DbtDebt> findByUserId(Long id) {
        List<DbtDebt> dbtDebtList = getDao().findAllByUsrUserId(id);
        return dbtDebtList;
    }

    public List<DbtDebt> findOverdueDebtByUser(Long userId) {
        Date nowDate=new Date();
        BigDecimal deptAmount = ZERO;
        List<DbtDebt> dbtDebtList = getDao().findAllByExpiryDateBeforeAndDebtAmountGreaterThanAndUsrUserId(nowDate,deptAmount,userId);
        return dbtDebtList;
    }
    public BigDecimal findTotalDebtByUser(Long userId) {
        List<DbtDebt> dbtDebtList = getDao().findAllByUsrUserId(userId);
        BigDecimal totalDebt = ZERO;
        totalDebt = dbtDebtList.stream()
                .map(DbtDebt::getDebtAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalDebt;
    }
    public BigDecimal findTotalOverdueDebtByUser(Long userId) {
        Date nowDate=new Date();
        BigDecimal deptAmount = new BigDecimal("0");
        List<DbtDebt> dbtDebtList = getDao().findAllByExpiryDateBeforeAndDebtAmountGreaterThanAndUsrUserId(nowDate,deptAmount,userId);
        BigDecimal totalOverdueDebt = ZERO;
        totalOverdueDebt = dbtDebtList.stream()
                .map(DbtDebt::getDebtAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalOverdueDebt;
    }
    public BigDecimal findLateFeeAmountByUser(Long userId) {
        Date nowDate=new Date();
        nowDate.setHours(23);
        nowDate.setMinutes(59);
        nowDate.setSeconds(59);
        BigDecimal lateFeeAmount = ZERO;

        List<DbtDebt> dbtDebtList = getDao().findAllByExpiryDateBeforeAndDebtAmountGreaterThanAndUsrUserId(nowDate,lateFeeAmount,userId);
        lateFeeAmount=calculateLateFeeAmountList(nowDate,dbtDebtList);

        return lateFeeAmount;
    }
    public BigDecimal findLateFeeAmountById(Long debtId) {
        Date nowDate=new Date();
        nowDate.setHours(23);
        nowDate.setMinutes(59);
        nowDate.setSeconds(59);
        BigDecimal lateFeeAmount = ZERO;

        DbtDebt dbtDebt =null;
        dbtDebt=getDao().findById(debtId).get();
        lateFeeAmount=calculateLateFeeAmount(nowDate,dbtDebt);

        return lateFeeAmount;
    }
    private BigDecimal calculateLateFeeAmountList(Date nowDate, List<DbtDebt> dbtDebtList){
        BigDecimal lateFeeAmount = ZERO;
        for(DbtDebt dbtDebt :dbtDebtList){
            long timeDiff = 0;
            timeDiff=nowDate.getTime()-dbtDebt.getExpiryDate().getTime();
            int daysDiff = (int) (timeDiff / (1000 * 60 * 60* 24));
            Double deptAmount = 0.0;
            if(daysDiff>0){
                if(new Date(2018,01,01).before(dbtDebt.getExpiryDate())){
                    deptAmount = daysDiff * 0.015 * dbtDebt.getMainDebt().doubleValue();
                }else{
                    deptAmount = daysDiff * 0.02 * dbtDebt.getMainDebt().doubleValue();
                }
            }
            if(deptAmount<1.0 && deptAmount > 0.0){
                deptAmount= 1.0;
            }
            lateFeeAmount= new BigDecimal(deptAmount).add(lateFeeAmount);
        }
        return  lateFeeAmount;
    }
    private BigDecimal calculateLateFeeAmount(Date nowDate, DbtDebt dbtDebt){
        BigDecimal lateFeeAmount = ZERO;
            long timeDiff = 0;
            timeDiff=nowDate.getTime()-dbtDebt.getExpiryDate().getTime();
            int daysDiff = (int) (timeDiff / (1000 * 60 * 60* 24));
            Double deptAmount = 0.0;
            if(daysDiff>0){
                if(new Date(2018,01,01).before(dbtDebt.getExpiryDate())){
                    deptAmount = daysDiff * 0.015 * dbtDebt.getMainDebt().doubleValue();
                }else{
                    deptAmount = daysDiff * 0.02 * dbtDebt.getMainDebt().doubleValue();
                }
            }
            if(deptAmount < 1.0 && deptAmount > 0.0){
                deptAmount= 1.0;
            }
            lateFeeAmount= new BigDecimal(deptAmount).add(lateFeeAmount);

        return  lateFeeAmount;
    }
}
