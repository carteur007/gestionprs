package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.missions.MisionsUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MisionUserRepository extends CrudRepository<MisionsUser, Long>{
    
}