package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.caissiers.Caissier;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaissierRepository extends CrudRepository<Caissier, Long>{
    
}