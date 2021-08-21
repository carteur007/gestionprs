package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.affectations.Affectation;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AffectationRepository extends CrudRepository<Affectation, Long>, CustomAffectationRepository{
    @Modifying
    @Transactional
    @Query("delete from Affectation g where g.user.id = ?1")
    void deleteByUserId(long userId);
    @Modifying
    @Transactional
    @Query("delete from Affectation g where g.groupement.id = ?1")
    void deleteByGroupementId(long groupementId);

}