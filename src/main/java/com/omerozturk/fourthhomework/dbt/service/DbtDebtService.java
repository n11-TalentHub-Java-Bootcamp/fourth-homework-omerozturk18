package com.omerozturk.fourthhomework.dbt.service;

import com.omerozturk.fourthhomework.dbt.entities.concretes.DbtDebt;
import com.omerozturk.fourthhomework.dbt.entities.dtos.DbtDebtDto;
import com.omerozturk.fourthhomework.dbt.entities.dtos.DbtDebtSaveRequestDto;
import com.omerozturk.fourthhomework.dbt.service.entityservice.DbtDebtEntityService;

import com.omerozturk.fourthhomework.dbt.utilities.converter.DbtDebtMapper;
import com.omerozturk.fourthhomework.gen.utilities.result.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DbtDebtService {

    private final DbtDebtEntityService dbtDebtEntityService;

    public DataResult<List<DbtDebtDto>> findAll() {
        List<DbtDebt> dbtDebtList = dbtDebtEntityService.findAll();
        List<DbtDebtDto> dbtDebtDtoList = DbtDebtMapper.INSTANCE.convertToDbtDebtDtoList(dbtDebtList);
        return new SuccessDataResult<List<DbtDebtDto>>(dbtDebtDtoList,"Tüm Borçlar Listelendi");
    }

    public DataResult<DbtDebtDto> findById(Long id) {
        DbtDebt dbtDebt = findDbtDebtById(id);
        DbtDebtDto dbtDebtDto = DbtDebtMapper.INSTANCE.convertToDbtDebtDtoList(dbtDebt);
        return new SuccessDataResult<DbtDebtDto>(dbtDebtDto,"Broç Listelendi");
    }

    public DataResult<DbtDebtDto> save(DbtDebtSaveRequestDto dbtDebtSaveRequestDto) {
        DbtDebt dbtDebt = DbtDebtMapper.INSTANCE.convertToDbtDebtSaveRequestDto(dbtDebtSaveRequestDto);
        dbtDebt = dbtDebtEntityService.save(dbtDebt);
        DbtDebtDto dbtDebtDto = DbtDebtMapper.INSTANCE.convertToDbtDebtDtoList(dbtDebt);
        return new SuccessDataResult<DbtDebtDto>(dbtDebtDto,"Borç Eklendi");
    }

    public Result delete(Long id) {
        DbtDebt dbtDebt = findDbtDebtById(id);
        if (dbtDebt==null){
            return new ErorrResult(" Borç Bulunamadı");
        }
        dbtDebtEntityService.delete(dbtDebt);
        return new SuccessResult(" Borç Silindi");
    }

    public DataResult<List<DbtDebtDto>> getByDateRange( Date startDate, Date endDate) {
        List<DbtDebt> dbtDebtList = dbtDebtEntityService.getByDateRange(startDate,endDate);
        List<DbtDebtDto> dbtDebtDtoList = DbtDebtMapper.INSTANCE.convertToDbtDebtDtoList(dbtDebtList);
        return new SuccessDataResult<List<DbtDebtDto>>(dbtDebtDtoList,"İstenilen Tarih Aralığındaki Borçlar Listelendi");
    }

    public DataResult<List<DbtDebtDto>>  findByUserId(Long id) {
        List<DbtDebt> dbtDebtList = dbtDebtEntityService.findByUserId(id);
        List<DbtDebtDto> dbtDebtDtoList = DbtDebtMapper.INSTANCE.convertToDbtDebtDtoList(dbtDebtList);
        return new SuccessDataResult<List<DbtDebtDto>>(dbtDebtDtoList,"Kullanıcıya Ait Borçlar Listeleniyor");
    }
    public DataResult<List<DbtDebtDto>> findOverdueDebtByUser(Long userId) {
        List<DbtDebt> dbtDebtList = dbtDebtEntityService.findOverdueDebtByUser(userId);
        List<DbtDebtDto> dbtDebtDtoList = DbtDebtMapper.INSTANCE.convertToDbtDebtDtoList(dbtDebtList);
        return new SuccessDataResult<List<DbtDebtDto>>(dbtDebtDtoList,"Kullanıcıya Ait Vadesi Geçmiş Borçlar Listeleniyor");
    }
    public DataResult<BigDecimal> findTotalDebtByUser(Long userId) {
        BigDecimal totalDebt =BigDecimal.ZERO;
        totalDebt=dbtDebtEntityService.findTotalDebtByUser(userId);
        return new SuccessDataResult<BigDecimal>(totalDebt,"Kullanıcıya Ait Toplam Borç Tutarı");
    }
    public DataResult<BigDecimal> findTotalOverdueDebtByUser(Long userId) {
        BigDecimal totalOverdueDebt =BigDecimal.ZERO;
        totalOverdueDebt=dbtDebtEntityService.findTotalOverdueDebtByUser(userId);
        return new SuccessDataResult<BigDecimal>(totalOverdueDebt,"Kullanıcıya Ait Toplam Vadesi Geçmiş Borç Tutarı");
    }
    public DataResult<BigDecimal> findLateFeeAmountByUser(Long userId) {
        BigDecimal total =BigDecimal.ZERO;
        total=dbtDebtEntityService.findLateFeeAmountByUser(userId);
        return new SuccessDataResult<BigDecimal>(total,"Kullanıcıya Ait Toplam Gecikme Zammı Tutarı");
    }


    private DbtDebt findDbtDebtById(Long id) {
        Optional<DbtDebt> optionalDbtDebt = dbtDebtEntityService.findById(id);
        DbtDebt dbtDebt;
        if (optionalDbtDebt.isPresent()){
            dbtDebt = optionalDbtDebt.get();
        } else {
            return  null;
        }
        return dbtDebt;
    }
}
