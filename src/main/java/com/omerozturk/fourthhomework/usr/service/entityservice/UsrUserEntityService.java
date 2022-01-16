package com.omerozturk.fourthhomework.usr.service.entityservice;


import com.omerozturk.fourthhomework.gen.utilities.service.BaseEntityService;
import com.omerozturk.fourthhomework.usr.dataAccess.abstracts.UsrUserDao;
import com.omerozturk.fourthhomework.usr.entities.concretes.UsrUser;
import org.springframework.stereotype.Service;

@Service
public class UsrUserEntityService extends BaseEntityService<UsrUser, UsrUserDao> {

    public UsrUserEntityService(UsrUserDao dao) {
        super(dao);
    }

    public UsrUser findByUsername(String username){
        return getDao().findByUsername(username);
    }
}
