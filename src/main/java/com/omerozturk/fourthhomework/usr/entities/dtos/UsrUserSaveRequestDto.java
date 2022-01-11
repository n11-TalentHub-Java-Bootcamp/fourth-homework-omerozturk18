package com.omerozturk.fourthhomework.usr.entities.dtos;

import com.omerozturk.fourthhomework.usr.entities.enums.EnumUsrUserType;
import lombok.Data;

@Data
public class UsrUserSaveRequestDto {

    private Long id;
    private String name;
    private String shortName;
    private String username;
    private String password;
    private String imageUrl;
    private EnumUsrUserType usrUserType;
}
