package com.omerozturk.fourthhomework.usr.utilities.converter;


import com.omerozturk.fourthhomework.usr.entities.concretes.UsrUser;
import com.omerozturk.fourthhomework.usr.entities.dtos.UsrUserDto;
import com.omerozturk.fourthhomework.usr.entities.dtos.UsrUserSaveRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrUserMapper {

    UsrUserMapper INSTANCE = Mappers.getMapper(UsrUserMapper.class);

    UsrUserDto convertToUsrUserDtoList(UsrUser usrUser);

    List<UsrUserDto> convertToUsrUserDtoList(List<UsrUser> usrUserList);

    UsrUser convertToUsrUserSaveRequestDto(UsrUserSaveRequestDto usrUserSaveRequestDto);
}
