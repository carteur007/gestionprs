package com.carteur.gestionprs.repositories;

import org.springframework.stereotype.Repository;

import com.carteur.gestionprs.legions.Legion;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface LegionRepository extends CrudRepository<Legion, Long>{
    
}