package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.groupements.Groupement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupementRepository extends JpaRepository<Groupement, Long> {

    Groupement findByNom(String nom);
    List<Groupement> findAllByLegion_Id(Long id);
    void deleteAllByLegion_Id(Long id);
    
}