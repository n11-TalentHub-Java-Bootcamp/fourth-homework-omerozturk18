package com.omerozturk.fourthhomework.pym.services;


import com.omerozturk.fourthhomework.gen.utilities.result.DataResult;
import com.omerozturk.fourthhomework.gen.utilities.result.Result;
import com.omerozturk.fourthhomework.gen.utilities.result.SuccessDataResult;
import com.omerozturk.fourthhomework.gen.utilities.result.SuccessResult;
import com.omerozturk.fourthhomework.pym.entities.concretes.PymPayment;
import com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentDto;
import com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentSaveRequestDto;
import com.omerozturk.fourthhomework.pym.services.entityservice.PymPaymentEntityService;
import com.omerozturk.fourthhomework.pym.utilities.converter.PymPaymentMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PymPaymentService {

    private final PymPaymentEntityService pymPaymentEntityService;

    public DataResult<List<PymPaymentDto>> findAll() {
        List<PymPayment> pymPaymentList = pymPaymentEntityService.findAll();
        List<PymPaymentDto> pymPaymentDtoList = PymPaymentMapper.INSTANCE.convertToPymPaymentDtoList(pymPaymentList);
        return new SuccessDataResult<List<PymPaymentDto>>(pymPaymentDtoList,"Veriler Listelendi");
    }

    public DataResult<PymPaymentDto> findById(Long id) {
        PymPayment pymPayment = findPymPaymentById(id);
        PymPaymentDto pymPaymentDto = PymPaymentMapper.INSTANCE.convertToPymPaymentDtoList(pymPayment);
        return new SuccessDataResult<PymPaymentDto>(pymPaymentDto,"Veri Listelendi");
    }

    public DataResult<PymPaymentDto> save(PymPaymentSaveRequestDto pymPaymentSaveRequestDto) {
        PymPayment pymPayment = PymPaymentMapper.INSTANCE.convertToPymPaymentSaveRequestDto(pymPaymentSaveRequestDto);
        pymPayment = pymPaymentEntityService.save(pymPayment);
        PymPaymentDto pymPaymentDto = PymPaymentMapper.INSTANCE.convertToPymPaymentDtoList(pymPayment);
        return new SuccessDataResult<PymPaymentDto>(pymPaymentDto,"Veri Eklendi");
    }

    public Result delete(Long id) {
        PymPayment pymPayment = findPymPaymentById(id);
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

    private PymPayment findPymPaymentById(Long id) {
        Optional<PymPayment> optionalPymPayment = pymPaymentEntityService.findById(id);
        PymPayment pymPayment;
        if (optionalPymPayment.isPresent()){
            pymPayment = optionalPymPayment.get();
        } else {
            throw new RuntimeException("User not found!");
        }
        return pymPayment;
    }
}
