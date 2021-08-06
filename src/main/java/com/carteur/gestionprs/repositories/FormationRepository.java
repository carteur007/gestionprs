package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.formations.Formation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends CrudRepository<Formation, Long>{
    
}