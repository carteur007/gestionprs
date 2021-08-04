package com.carteur.gestionprs.repositories;

import com.carteur.gestionprs.grades.Grade;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long>{
    
}