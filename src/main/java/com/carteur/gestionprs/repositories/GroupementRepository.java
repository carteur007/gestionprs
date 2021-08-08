package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.groupements.Groupement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GroupementRepository extends CrudRepository<Groupement, Long>{
    Page<Groupement> findByLegionId(long legionId, Pageable pageable);
    @Modifying
    @Transactional
    @Query("delete from Groupement g where g.legion.id = ?1")
    void deleteByLegionId(long legionId);

}