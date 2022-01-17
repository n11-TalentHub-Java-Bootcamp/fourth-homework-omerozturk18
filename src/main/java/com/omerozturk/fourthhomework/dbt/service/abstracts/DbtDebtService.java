package com.omerozturk.fourthhomework.dbt.service.abstracts;

import com.omerozturk.fourthhomework.dbt.entities.dtos.DbtDebtDto;
import com.omerozturk.fourthhomework.dbt.entities.dtos.DbtDebtSaveRequestDto;
import com.omerozturk.fourthhomework.gen.utilities.result.DataResult;
import com.omerozturk.fourthhomework.gen.utilities.result.Result;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface DbtDebtService {
    DataResult<List<DbtDebtDto>> findAll();
    DataResult<DbtDebtDto> findById(Long id);
    DataResult<DbtDebtDto> save(DbtDebtSaveRequestDto dbtDebtSaveRequestDto);
    Result delete(Long id);
    DataResult<List<DbtDebtDto>> getByDateRange(Date startDate, Date endDate);
    DataResult<List<DbtDebtDto>>  findByUserId(Long id);
    DataResult<List<DbtDebtDto>> findOverdueDebtByUser(Long userId);
    DataResult<BigDecimal> findTotalDebtByUser(Long userId);
    DataResult<BigDecimal> findTotalOverdueDebtByUser(Long userId);
    DataResult<BigDecimal> findLateFeeAmountByUser(Long userId);
}
