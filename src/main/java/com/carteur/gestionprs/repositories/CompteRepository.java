package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.comptes.Compte;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends CrudRepository<Compte, Long>{
    
}