package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.affectations.Affectation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ImplAffectationRepository implements CustomAffectationRepository {

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Affectation> findAffectationByUserId(long id_user) {
        String sql = "SELECT af.* FROM Affectation af " + "WHERE af.user.id  = ?1";
        Query query = entityManager.createQuery(sql, Affectation.class);
        query.setParameter(1, id_user);
        return query.getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<Affectation> findAffectationByGroupementId(long id_groupement) {
        String sql = "SELECT af.* FROM Affectation af " + "WHERE af.groupement.id  = ?1";
        Query query = entityManager.createQuery(sql, Affectation.class);
        query.setParameter(1, id_groupement);
        return query.getResultList();
    }
    @SuppressWarnings("unchecked")
    public List<Affectation> findAffectationByGroupementIdAndUserId(long id_groupement, long id_user) {
        String sql = "SELECT af.* FROM Affectation af " + "WHERE af.groupement.id  = ?1 AND af.user.id  = ?2";
        Query query = entityManager.createQuery(sql, Affectation.class);
        query.setParameter(1, id_groupement);
        query.setParameter(2, id_user);
        return query.getResultList();
    }
}