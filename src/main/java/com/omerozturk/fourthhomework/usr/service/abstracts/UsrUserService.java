package com.omerozturk.fourthhomework.usr.service.abstracts;

import com.omerozturk.fourthhomework.gen.utilities.result.DataResult;
import com.omerozturk.fourthhomework.gen.utilities.result.Result;
import com.omerozturk.fourthhomework.usr.entities.dtos.UsrUserDto;
import com.omerozturk.fourthhomework.usr.entities.dtos.UsrUserSaveRequestDto;

import java.util.List;

public interface UsrUserService {
    DataResult<List<UsrUserDto>> findAll();
    DataResult<UsrUserDto> findById(Long id);
    DataResult<UsrUserDto> findByUsername(String username);
    DataResult<UsrUserDto> save(UsrUserSaveRequestDto usrUserSaveRequestDto);
    Result delete(Long id);
}
