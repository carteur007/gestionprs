package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.formations.Formation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FormationRepository extends CrudRepository<Formation, Long>{
    
    Page<Formation> findByUserId(long userId, Pageable pageable);
    @Modifying
    @Transactional
    @Query("delete from Formation g where g.user.id = ?1")
    void deleteByUserId(long userId);
}