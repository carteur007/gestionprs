package com.carteur.gestionprs.repositories;


import com.carteur.gestionprs.grades.Grade;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {
    
    @Modifying
    @Transactional
    @Query("delete from Grade g where g.user.id = ?1")
    void deleteByUserId(long userId);
}