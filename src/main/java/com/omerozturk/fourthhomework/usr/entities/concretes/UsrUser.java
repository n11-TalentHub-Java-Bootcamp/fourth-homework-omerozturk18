package com.omerozturk.fourthhomework.usr.entities.concretes;

import com.omerozturk.fourthhomework.gen.utilities.entity.BaseEntity;
import com.omerozturk.fourthhomework.usr.entities.enums.EnumUsrUserType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USR_USER")
@Data
public class UsrUser implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String shortName;
    private String username;
    private String password;
    private String imageUrl;
    private EnumUsrUserType usrUserType;
}
