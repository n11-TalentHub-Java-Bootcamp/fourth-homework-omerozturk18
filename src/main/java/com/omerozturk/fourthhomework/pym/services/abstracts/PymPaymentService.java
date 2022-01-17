package com.omerozturk.fourthhomework.pym.services.abstracts;

import com.omerozturk.fourthhomework.gen.utilities.result.DataResult;
import com.omerozturk.fourthhomework.gen.utilities.result.Result;
import com.omerozturk.fourthhomework.pym.entities.concretes.PymPayment;
import com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentDto;
import com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentSaveRequestDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PymPaymentService {
    DataResult<List<PymPaymentDto>> findAll();
    DataResult<PymPaymentDto> findById(Long id);
    DataResult<List<PymPaymentDto>> save(PymPaymentSaveRequestDto pymPaymentSaveRequestDto);
    Result delete(Long id);
    DataResult<List<PymPaymentDto>> getByDateRange(Date startDate, Date endDate);
    DataResult<List<PymPaymentDto>> findByUserId(Long id);
    DataResult<BigDecimal> findTotalPaidLateFeeAmountByUser(Long userId);
    DataResult<List<PymPaymentDto>> findPaidLateFeesByUser(Long id);
}
