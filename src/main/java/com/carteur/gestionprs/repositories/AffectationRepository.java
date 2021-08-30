package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.affectations.Affectation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long>{
    @Modifying
    @Transactional
    void deleteByCompte_Id(Long id);
    @Modifying
    @Transactional
    void deleteByGroupement_Id(Long id);

    List<Affectation> findAllByCompte_Id(Long id);
    List<Affectation> findAllByGroupement_Id(Long id);

}