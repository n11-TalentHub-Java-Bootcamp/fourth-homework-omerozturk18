package com.omerozturk.fourthhomework.pym.utilities.converter;



import com.omerozturk.fourthhomework.pym.entities.concretes.PymPayment;
import com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentDto;
import com.omerozturk.fourthhomework.pym.entities.dtos.PymPaymentSaveRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PymPaymentMapper {

    PymPaymentMapper INSTANCE = Mappers.getMapper(PymPaymentMapper.class);

    PymPaymentDto convertToPymPaymentDtoList(PymPayment pymPayment);

    List<PymPaymentDto> convertToPymPaymentDtoList(List<PymPayment> pymPaymentList);

    PymPayment convertToPymPaymentSaveRequestDto(PymPaymentSaveRequestDto pymPaymentSaveRequestDto);
}
