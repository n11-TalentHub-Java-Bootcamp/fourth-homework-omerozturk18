package com.omerozturk.fourthhomework.dbt.utilities.converter;


import com.omerozturk.fourthhomework.dbt.entities.concretes.DbtDebt;
import com.omerozturk.fourthhomework.dbt.entities.dtos.DbtDebtDto;
import com.omerozturk.fourthhomework.dbt.entities.dtos.DbtDebtSaveRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DbtDebtMapper {

    DbtDebtMapper INSTANCE = Mappers.getMapper(DbtDebtMapper.class);

    DbtDebtDto convertToDbtDebtDtoList(DbtDebt dbtDebt);

    List<DbtDebtDto> convertToDbtDebtDtoList(List<DbtDebt> dbtDebtList);

    DbtDebt convertToDbtDebtSaveRequestDto(DbtDebtSaveRequestDto dbtDebtSaveRequestDto);
}
