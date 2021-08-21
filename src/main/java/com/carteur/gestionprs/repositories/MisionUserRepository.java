package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.missions.MisionsUser;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MisionUserRepository extends CrudRepository<MisionsUser, Long>,CustomMisionUserRepository{
   
    @Modifying
    @Transactional
    @Query("delete from MisionsUser g where g.user.id = ?1")
    void deleteByUserId(long userId);
    @Modifying
    @Transactional
    @Query("delete from MisionsUser g where g.mision.id = ?1")
    void deleteByMisionUserId(long misionId);

}