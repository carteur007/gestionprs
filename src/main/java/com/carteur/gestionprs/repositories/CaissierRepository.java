package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.caissiers.Caissier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CaissierRepository extends CrudRepository<Caissier, Long>{
    
    Page<Caissier> findByUserId(long userId, Pageable pageable);
    @Modifying
    @Transactional
    @Query("delete from Caissier g where g.user.id = ?1")
    void deleteByUserId(long userId);
}