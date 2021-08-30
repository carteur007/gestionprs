package com.carteur.gestionprs.repositories;

import java.util.List;

import com.carteur.gestionprs.affectations.Affectation;

public interface CustomAffectationRepository {
    
    public List<Affectation> findAffectationByCompteId(Long id);
    public List<Affectation> findAffectationByGroupementId(Long id);
    public List<Affectation> findAffectationByGroupementIdAndCompteId(Long id_group, Long id_compte);
    
}