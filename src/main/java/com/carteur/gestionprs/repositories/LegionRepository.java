package com.carteur.gestionprs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carteur.gestionprs.legions.Legion;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface LegionRepository extends JpaRepository<Legion, Long> {

    Legion findByNom(String nom);
    
}