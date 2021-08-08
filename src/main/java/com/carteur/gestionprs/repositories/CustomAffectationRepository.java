package com.carteur.gestionprs.repositories;

import java.util.List;

import com.carteur.gestionprs.affectations.Affectation;

public interface CustomAffectationRepository {
    
    public List<Affectation> findAffectationByUserId(long id_user);
    public List<Affectation> findAffectationByGroupementId(long id_user);
    public List<Affectation> findAffectationByGroupementIdAndUserId(long id_group, long id_user);
    
}