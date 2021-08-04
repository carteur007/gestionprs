package com.carteur.gestionprs.controllers;

import java.util.ArrayList;

import com.carteur.gestionprs.affectations.Affectation;
import com.carteur.gestionprs.repositories.AffectationRepository;
import com.carteur.gestionprs.repositories.GroupementRepository;
import com.carteur.gestionprs.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AffectationController {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    AffectationRepository affectationRepository;
    @Autowired
    GroupementRepository groupementRepository ;
    /**
     * Recherche les affectations
     * @return
     */
    @GetMapping("/affectations")
    public ResponseEntity<List<Affectation>>  getAllAffectations() {
        try {
            List<Affectation> affecList = new ArrayList<Affectation>(); 
            affectationRepository.findAll().forEach(affecList::add);  
            if(affecList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            return new ResponseEntity<>(affecList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher d'une affectations par son id
     * @param id
     * @return
     */
    @GetMapping("/affectations/{id}")
	public ResponseEntity<Affectation> getAffectationById(@PathVariable("id") long id) {
        Optional<Affectation> affectationData = affectationRepository.findById(id);

		if (affectationData.isPresent()) {
			return new ResponseEntity<>(affectationData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    /**
     * Creation d'une affectation
     * @param affect
     * @return
     */
    @PostMapping(value="/affectations")
    public ResponseEntity<Affectation> createAffectation(@RequestBody Affectation affect ) {
        try {
            Affectation _affect = affectationRepository.save(affect);
            return new ResponseEntity<>(_affect, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Mise a jour d'une affectation
     * @param id
     * @param affect
     * @return
     */
    @PutMapping(value="/affectations/{id}")
    public ResponseEntity<Affectation> updateAffectation(@PathVariable("id") long id, @RequestBody Affectation affect) {
        try {
            Optional<Affectation> affectData = affectationRepository.findById(id);
            if(affectData.isPresent()){
                return new ResponseEntity<>(affectationRepository.save(affect), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression d'un utilisateur
     * @param id
     * @return
     */
    @DeleteMapping(value="/affectations/{id}")
    public ResponseEntity<List<Affectation>> deleteAffectationById(@PathVariable("id") long id) {
        try {
            affectationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression de tous les utilisateurs
     * @return
     */
    @DeleteMapping(value="/affectations")
    public ResponseEntity<List<Affectation>> deleteAllAffectation() {
        try {
            affectationRepository.deleteAll();;
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
}