package com.omerozturk.fourthhomework.usr.entities.concretes;

import com.omerozturk.fourthhomework.gen.utilities.entity.BaseEntity;
import com.omerozturk.fourthhomework.usr.entities.enums.EnumUsrUserType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USR_USER")
@Data
public class UsrUser implements BaseEntity {

    @Id
    @GeneratedValue(generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "USR_USER_ID_SEQ")
    private Long id;
    private String name;
    private String shortName;
    private String username;
    private String password;
    private String imageUrl;
    private EnumUsrUserType usrUserType;
}
