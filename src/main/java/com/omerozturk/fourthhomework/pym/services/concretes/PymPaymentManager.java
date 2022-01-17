package com.omerozturk.fourthhomework.pym.services.concretes;


import com.omerozturk.fourthhomework.dbt.entities.concretes.DbtDebt;
import com.omerozturk.fourthhomework.dbt.entities.enums.EnumDbtDebtType;
import com.omerozturk.fourthhomework.dbt.service.entityservice.DbtDebtEntityService;
import com.omerozturk.fourthhomework.dbt.utilities.exception.DbtDebtNotFoundException;
import com.omerozturk.fourthhomework.gen.utilities.result.*;
import com.omerozturk.fourthhomework.pym.entities.concretes.PymPayment;
import com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentDto;
import com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentSaveRequestDto;
import com.omerozturk.fourthhomework.pym.services.abstracts.PymPaymentService;
import com.omerozturk.fourthhomework.pym.services.entityservice.PymPaymentEntityService;
import com.omerozturk.fourthhomework.pym.utilities.converter.PymPaymentMapper;

import com.omerozturk.fourthhomework.pym.utilities.exception.PymPaymentNotFoundException;
import com.omerozturk.fourthhomework.usr.entities.dtos.UsrUserDto;
import com.omerozturk.fourthhomework.usr.service.abstracts.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PymPaymentManager implements PymPaymentService {

    private final PymPaymentEntityService pymPaymentEntityService;
    private final DbtDebtEntityService dbtDebtEntityService;
    private final UsrUserService usrUserService;

    public DataResult<List<PymPaymentDto>> findAll() {
        List<PymPayment> pymPaymentList = pymPaymentEntityService.findAll();
        List<PymPaymentDto> pymPaymentDtoList = PymPaymentMapper.INSTANCE.convertToPymPaymentDtoList(pymPaymentList);
        return new SuccessDataResult<List<PymPaymentDto>>(pymPaymentDtoList,"Veriler Listelendi");
    }

    public DataResult<PymPaymentDto> findById(Long id) {
        PymPayment pymPayment = pymPaymentEntityService.findById(id);
        if (pymPayment == null){
            throw new PymPaymentNotFoundException("Ödeme Bulunanamdı!");
        }
        PymPaymentDto pymPaymentDto = PymPaymentMapper.INSTANCE.convertToPymPaymentDtoList(pymPayment);
        return new SuccessDataResult<PymPaymentDto>(pymPaymentDto,"Veri Listelendi");
    }

    @Transactional
    public DataResult<List<PymPaymentDto>> save(PymPaymentSaveRequestDto pymPaymentSaveRequestDto) {
        List<PymPayment> pymPaymentList=new ArrayList<>();
        PymPayment pymPayment = PymPaymentMapper.INSTANCE.convertToPymPaymentSaveRequestDto(pymPaymentSaveRequestDto);
        DataResult<UsrUserDto> usrUserDtoDataResult = usrUserService.findById(pymPayment.getUsrUserId());
        DbtDebt dbtDebt = dbtDebtEntityService.findById(pymPayment.getDbtDebtId());
        pymPaymentEntityService.save(pymPayment);
        if (dbtDebt==null || dbtDebt.getDebtAmount().intValue()==0){
            throw new DbtDebtNotFoundException("Borç Bulunamadı");
        }
        DataResult<DbtDebt> dbtDebtDataResult=saveDbtDebtAndDbtDebtLateFee(dbtDebt,pymPayment.getPaymentAmount());
        pymPayment.setPaymentAmount(dbtDebt.getMainDebt());
        pymPayment = pymPaymentEntityService.save(pymPayment);
        pymPaymentList.add(pymPayment);
        DbtDebt dbtDebtResponse=dbtDebtDataResult.getData();
        if(dbtDebtResponse.getDebtType()==EnumDbtDebtType.DELAY_HIKE){
            PymPayment pymPaymentLateFee=new PymPayment();
            pymPaymentLateFee.setExplanation("Gecikme Cezası Ödemesi");
            pymPaymentLateFee.setPaymentAmount(dbtDebtResponse.getMainDebt());
            pymPaymentLateFee.setPaymentDate(new Date());
            pymPaymentLateFee.setDbtDebtId(dbtDebtResponse.getId());
            pymPaymentLateFee.setUsrUserId(dbtDebtResponse.getUsrUserId());
            pymPaymentLateFee = pymPaymentEntityService.save(pymPaymentLateFee);
            pymPaymentList.add(pymPaymentLateFee);

        }

        List<PymPaymentDto> pymPaymentDtoList = PymPaymentMapper.INSTANCE.convertToPymPaymentDtoList(pymPaymentList);
        return new SuccessDataResult<List<PymPaymentDto>>(pymPaymentDtoList,"Tahsilat Yapıldı");
    }

    public Result delete(Long id) {
        PymPayment pymPayment = pymPaymentEntityService.findById(id);
        if (pymPayment == null){
            throw new PymPaymentNotFoundException("Ödeme Bulunanamdı!");
        }
        pymPaymentEntityService.delete(pymPayment);
        return new SuccessResult(" Veri Silindi");
    }

    public DataResult<List<PymPaymentDto>> getByDateRange(Date startDate, Date endDate) {
        List<PymPayment> pymPaymentList = pymPaymentEntityService.getByDateRange(startDate,endDate);
        List<PymPaymentDto> pymPaymentDtoList = PymPaymentMapper.INSTANCE.convertToPymPaymentDtoList(pymPaymentList);
        return new SuccessDataResult<List<PymPaymentDto>>(pymPaymentDtoList,"Veriler Listelendi");
    }

    public DataResult<List<PymPaymentDto>> findByUserId(Long id) {
        List<PymPayment> pymPaymentList = pymPaymentEntityService.findByUserId(id);
        List<PymPaymentDto> pymPaymentDtoList = PymPaymentMapper.INSTANCE.convertToPymPaymentDtoList(pymPaymentList);
        return new SuccessDataResult<List<PymPaymentDto>>(pymPaymentDtoList,"Kullanıcıya Ait Tahsilatlar Listeleniyor");
    }
    public DataResult<BigDecimal> findTotalPaidLateFeeAmountByUser(Long userId) {
        BigDecimal total =BigDecimal.ZERO;
        total=pymPaymentEntityService.findTotalPaidLateFeeAmountByUser(userId);
        return new SuccessDataResult<BigDecimal>(total,"Toplam Ödenen Gecikme Zammı Tutarı");
    }
    public DataResult<List<PymPaymentDto>> findPaidLateFeesByUser(Long id) {
        List<PymPayment> pymPaymentList = pymPaymentEntityService.findPaidLateFeesByUser(id);
        List<PymPaymentDto> pymPaymentDtoList = PymPaymentMapper.INSTANCE.convertToPymPaymentDtoList(pymPaymentList);
        return new SuccessDataResult<List<PymPaymentDto>>(pymPaymentDtoList,"Kullanın Ödediği Gecikme Cezaları Listeleniyor");
    }



    private DataResult<DbtDebt> saveDbtDebtAndDbtDebtLateFee(DbtDebt dbtDebt,BigDecimal paymentAmount) {

        dbtDebt.setDebtAmount(new BigDecimal(0));
        BigDecimal lateFeeAmount=BigDecimal.ZERO;
        lateFeeAmount = dbtDebtEntityService.findLateFeeAmountById(dbtDebt.getId());
        BigDecimal totolDebtAmount=BigDecimal.ZERO;
        totolDebtAmount= new BigDecimal(String.valueOf(dbtDebt.getMainDebt())).add(lateFeeAmount);
        if( totolDebtAmount.intValue()!=paymentAmount.intValue()){
            throw new PymPaymentNotFoundException("Toplam Borç Tutarı ile Girilen Ödeme Tutarı Eşleşmiyor. Topma Borç="+totolDebtAmount);
        }
        dbtDebtEntityService.save(dbtDebt);
        if(lateFeeAmount!=BigDecimal.ZERO){
            return saveDbtDebtLateFee( dbtDebt, lateFeeAmount);
        }
        return new SuccessDataResult<>(dbtDebt) ;
    }
    private DataResult<DbtDebt> saveDbtDebtLateFee(DbtDebt dbtDebt,BigDecimal lateFeeAmount){
        DbtDebt dbtDebtLateFee=new DbtDebt();
        dbtDebtLateFee.setExplanation("Gecikme Cezası");
        dbtDebtLateFee.setExpiryDate(new Date());
        dbtDebtLateFee.setMainDebt(lateFeeAmount);
        dbtDebtLateFee.setDebtAmount(BigDecimal.ZERO);
        dbtDebtLateFee.setDebtType(EnumDbtDebtType.DELAY_HIKE);
        dbtDebtLateFee.setUsrUserId(dbtDebt.getUsrUserId());
        dbtDebtLateFee.setTopDebtId(dbtDebt.getId());

        dbtDebtLateFee= dbtDebtEntityService.save(dbtDebtLateFee);
        return new SuccessDataResult<>(dbtDebtLateFee);
    }

}
