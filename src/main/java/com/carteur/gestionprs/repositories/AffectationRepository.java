package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.affectations.Affectation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffectationRepository extends CrudRepository<Affectation, Long>, CustomAffectationRepository{

    
    
}