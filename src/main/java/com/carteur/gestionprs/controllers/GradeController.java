package com.carteur.gestionprs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.grades.Grade;
import com.carteur.gestionprs.repositories.GradeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class GradeController {
    
    @Autowired
    GradeRepository gradeRepository;

    /**
     * Recherche les grades
     * @return
     */
    @GetMapping("/grades")
    public ResponseEntity<List<Grade>>  getgrades() {
        try {
            List<Grade> gradeList = new ArrayList<Grade>(); 
            gradeRepository.findAll().forEach(gradeList::add);   
            if(gradeList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            return new ResponseEntity<>(gradeList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Grade>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher d'un Grade par son id
     * @param id
     * @return
     */
    @GetMapping("/grades/{id}")
	public ResponseEntity<Grade> getUserById(@PathVariable("id") long id) {
        Optional<Grade> gradeData = gradeRepository.findById(id);

		if (gradeData.isPresent()) {
			return new ResponseEntity<>(gradeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    /**
     * Creation d'un Grade
     * @param grade
     * @return
     */
    @PostMapping(value="/grades")
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade ) {
        try {
            Grade _grade = gradeRepository.save(grade);
            return new ResponseEntity<>(_grade, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Mise a jour d'un Grade
     * @param id
     * @param grade
     * @return
     */
    @PutMapping(value="/grades/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable("id") long id, @RequestBody Grade grade) {
        try {
            Optional<Grade> gradeData = gradeRepository.findById(id);
            if(gradeData.isPresent()){
                return new ResponseEntity<>(gradeRepository.save(grade), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression d'un Grade
     * @param id
     * @return
     */
    @DeleteMapping(value="/grades/{id}")
    public ResponseEntity<List<Grade>> deleteGradeById(@PathVariable("id") long id) {
        try {
      gradeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression de tous les grades
     * @return
     */
    @DeleteMapping(value="/grades")
    public ResponseEntity<List<Grade>> deleteAllGrade() {
        try {
      gradeRepository.deleteAll();;
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}