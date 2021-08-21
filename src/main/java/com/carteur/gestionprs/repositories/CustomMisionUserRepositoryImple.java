package com.carteur.gestionprs.repositories;

import java.util.*;
import com.carteur.gestionprs.missions.MisionsUser;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomMisionUserRepositoryImple implements CustomMisionUserRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<MisionsUser> findMisionsUserByUserId(long id_user){
        String sql = "select mu.* from MisionsUser mu where mu.user.id = ?1";
        Query query = entityManager.createNativeQuery(sql, MisionsUser.class);
        query.setParameter(1, id_user);
        return query.getResultList();
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<MisionsUser> findMisionsUserByMisionId(long id_mission){
        String  sql = "select mu.* from MisionsUser mu where mu.mission.id = ?1";
        Query query = entityManager.createQuery(sql, MisionsUser.class);
        query.setParameter(1, id_mission);
        return query.getResultList();
    }
    @Override
    @SuppressWarnings("unchecked")    
    public List<MisionsUser> findMisionsUserByMisionIdAndUserId(long id_mission, long id_user){
        String sql = "select mu.* from MisionsUser mu where mu.mision.id = ?1 and mu.user.id = ?2";
        Query query = entityManager.createQuery(sql, MisionsUser.class);
        query.setParameter(2, id_user);
        query.setParameter(1, id_mission);
        return query.getResultList();
    }

}