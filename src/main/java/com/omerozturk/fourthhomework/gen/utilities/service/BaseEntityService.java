package com.omerozturk.fourthhomework.gen.utilities.service;

import com.omerozturk.fourthhomework.dbt.entities.concretes.DbtDebt;
import com.omerozturk.fourthhomework.gen.utilities.entity.BaseEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity,D extends JpaRepository> {

    private D dao;

    public List<E> findAll(){
        return dao.findAll();
    }

    public E findById(Long id){
        Optional<E> optionalEntity=dao.findById(id);
        E entity=null;
        if (optionalEntity.isPresent()){
            entity = optionalEntity.get();
        }
        return entity;
    }

    public E save(E e){
        return (E) dao.save(e);
    }

    public void delete(E e){
        dao.delete(e);
    }

    public D getDao() {
        return dao;
    }
}
