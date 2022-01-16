package com.omerozturk.fourthhomework.usr.service;


import com.omerozturk.fourthhomework.gen.utilities.result.DataResult;
import com.omerozturk.fourthhomework.gen.utilities.result.Result;
import com.omerozturk.fourthhomework.gen.utilities.result.SuccessDataResult;
import com.omerozturk.fourthhomework.gen.utilities.result.SuccessResult;
import com.omerozturk.fourthhomework.usr.entities.concretes.UsrUser;
import com.omerozturk.fourthhomework.usr.entities.dtos.UsrUserDto;
import com.omerozturk.fourthhomework.usr.entities.dtos.UsrUserSaveRequestDto;
import com.omerozturk.fourthhomework.usr.service.entityservice.UsrUserEntityService;
import com.omerozturk.fourthhomework.usr.utilities.converter.UsrUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsrUserService {

    private final UsrUserEntityService usrUserEntityService;

    public DataResult<List<UsrUserDto>> findAll() {
        List<UsrUser> usrUserList = usrUserEntityService.findAll();
        List<UsrUserDto> usrUserDtoList = UsrUserMapper.INSTANCE.convertToUsrUserDtoList(usrUserList);
        return new SuccessDataResult<List<UsrUserDto>>(usrUserDtoList,"Veriler Listelendi");
    }

    public DataResult<UsrUserDto> findById(Long id) {
        UsrUser usrUser = findUsrUserById(id);
        UsrUserDto usrUserDto = UsrUserMapper.INSTANCE.convertToUsrUserDtoList(usrUser);
        return new SuccessDataResult<UsrUserDto>(usrUserDto,"Veri Listelendi");
    }

    public DataResult<UsrUserDto> findByUsername(String username) {
        UsrUser usrUser = usrUserEntityService.findByUsername(username);
        if (usrUser == null){
            throw new RuntimeException("User not found!");
        }
        UsrUserDto usrUserDto = UsrUserMapper.INSTANCE.convertToUsrUserDtoList(usrUser);
        return new SuccessDataResult<UsrUserDto>(usrUserDto,"Veri Listelendi");
    }

    public DataResult<UsrUserDto> save(UsrUserSaveRequestDto usrUserSaveRequestDto) {
        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUserSaveRequestDto(usrUserSaveRequestDto);
        usrUser = usrUserEntityService.save(usrUser);
        UsrUserDto usrUserDto = UsrUserMapper.INSTANCE.convertToUsrUserDtoList(usrUser);
        return new SuccessDataResult<UsrUserDto>(usrUserDto,"Veri Eklendi");
    }

    public Result delete(Long id) {
        UsrUser usrUser = findUsrUserById(id);
        usrUserEntityService.delete(usrUser);
        return new SuccessResult(" Veri Silindi");
    }

    private UsrUser findUsrUserById(Long id) {
        Optional<UsrUser> optionalUsrUser = usrUserEntityService.findById(id);
        UsrUser usrUser;
        if (optionalUsrUser.isPresent()){
            usrUser = optionalUsrUser.get();
        } else {
            throw new RuntimeException("User not found!");
        }
        return usrUser;
    }
}
