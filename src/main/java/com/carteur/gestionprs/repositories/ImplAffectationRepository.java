package com.carteur.gestionprs.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.carteur.gestionprs.affectations.Affectation;
import com.carteur.gestionprs.groupements.Groupement;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ImplAffectationRepository implements CustomAffectationRepository {

    @PersistenceContext
    EntityManager entityManager;
/*
    @SuppressWarnings("unchecked")
    public List<Affectation> findAffectationByIdUser(long id_user) {
        String sql = "SELECT af.* FROM Affectation af " + "WHERE af.user_id  = ?1";
        Query query = entityManager.createQuery(sql, Affectation.class);
        query.setParameter(1, id_user);

        return query.getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<Affectation> findAffectationByIdGroupement(long id_groupement) {
        String sql = "SELECT af.* FROM Affectation af " + "WHERE af.groupement_id  = ?1";
        Query query = entityManager.createQuery(sql, Affectation.class);
        query.setParameter(1, id_groupement);

        return query.getResultList();
    }

    */
}