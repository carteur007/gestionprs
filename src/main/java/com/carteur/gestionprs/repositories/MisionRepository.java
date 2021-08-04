package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.missions.Mision;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MisionRepository extends CrudRepository<Mision, Long>{
    
}