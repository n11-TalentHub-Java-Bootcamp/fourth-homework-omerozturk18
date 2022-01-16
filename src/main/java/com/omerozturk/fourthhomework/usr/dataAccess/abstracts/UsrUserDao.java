package com.omerozturk.fourthhomework.usr.dataAccess.abstracts;


import com.omerozturk.fourthhomework.usr.entities.concretes.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsrUserDao extends JpaRepository<UsrUser, Long> {

    UsrUser findByUsername(String username);
}
