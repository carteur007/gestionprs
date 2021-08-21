package com.carteur.gestionprs.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.grades.Grade;
import com.carteur.gestionprs.repositories.GradeRepository;
import com.carteur.gestionprs.repositories.UserRepository;
import com.carteur.gestionprs.users.User;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class GradeController {

    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeController(UserRepository userRepository, GradeRepository gradeRepository) {
        this.userRepository = userRepository;
        this.gradeRepository = gradeRepository;
    }
    /**
     * Recherche les grades
     * 
     * @return
     */
    @GetMapping("/grades")
    public ResponseEntity<List<Grade>> getgrades() {
        try {
            List<Grade> gradeList = new ArrayList<Grade>();
            gradeRepository.findAll().forEach(gradeList::add);
            if (gradeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(gradeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Grade>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher d'un Grade par son id
     * 
     * @param id
     * @return
     */
    @GetMapping("/grades/{id}")
    public ResponseEntity<Grade> getUserById(@PathVariable("id") long id) {
        Optional<Grade> gradeData = gradeRepository.findById(id);
        if (!gradeData.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return new ResponseEntity<>(gradeData.get(),HttpStatus.OK);
    }
    /**
     * Creation d'un Grade
     * 
     * @param grade
     * @return
     */
    @PostMapping(value = "/grades")
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        try {
            Optional<User> optionalUser = userRepository.findById(grade.getUser().getId());
            if (!optionalUser.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            grade.setUser(optionalUser.get());
            Grade _grade = gradeRepository.save(grade);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(_grade.getId()).toUri();
            return ResponseEntity.created(location).body(_grade);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Mise a jour d'un Grade
     * 
     * @param id
     * @param grade
     * @return
     */
    @PutMapping(value = "/grades/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable("id") long id, @RequestBody Grade grade) {
        try {
            Optional<Grade> gradeData = gradeRepository.findById(id);
            Optional<User> optionalUser = userRepository.findById(gradeData.get().getUser().getId());
            if (!optionalUser.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            if (!gradeData.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            grade.setUser(optionalUser.get());
            grade.setId(gradeData.get().getId());
            return new ResponseEntity<>(gradeRepository.save(grade), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Suppression d'un Grade
     * 
     * @param id
     * @return
     */
    @DeleteMapping(value = "/grades/{id}")
    public ResponseEntity<List<Grade>> deleteGradeById(@PathVariable("id") long id) {
        try {
            Optional<Grade> gradeData = gradeRepository.findById(id);
            if (!gradeData.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            gradeRepository.delete(gradeData.get());
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Suppression de tous les grades
     * 
     * @return
     */
    @DeleteMapping(value = "/grades")
    public ResponseEntity<List<Grade>> deleteAllGrade() {
        try {
            gradeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}