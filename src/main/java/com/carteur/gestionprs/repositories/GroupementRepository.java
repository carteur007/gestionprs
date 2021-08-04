package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.groupements.Groupement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupementRepository extends CrudRepository<Groupement, Long>{
    
}