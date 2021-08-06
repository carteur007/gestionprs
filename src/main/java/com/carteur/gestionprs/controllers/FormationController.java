package com.carteur.gestionprs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.formations.Formation;
import com.carteur.gestionprs.repositories.FormationRepository;

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
public class FormationController {

    @Autowired
    FormationRepository formationRepository;

    /**
     * Recherche les Formations
     * @return
     */
    @GetMapping("/formations")
    public ResponseEntity<List<Formation>>  getFormations() {
        try {
            List<Formation> formationList = new ArrayList<Formation>(); 
            formationRepository.findAll().forEach(formationList::add);   
            if(formationList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            return new ResponseEntity<>(formationList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher d'un Formation par son id
     * @param id
     * @return
     */
    @GetMapping("/formations/{id}")
	public ResponseEntity<Formation> getFormationById(@PathVariable("id") long id) {
        Optional<Formation> formationData = formationRepository.findById(id);

		if (formationData.isPresent()) {
			return new ResponseEntity<>(formationData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    /**
     * Creation d'un Formation
     * @param formation
     * @return
     */
    @PostMapping(value="/formations")
    public ResponseEntity<Formation> createFormation(@RequestBody Formation formation ) {
        try {
            Formation _formation = formationRepository.save(formation);
            return new ResponseEntity<>(_formation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Mise a jour d'un Formation
     * @param id
     * @param formation
     * @return
     */
    @PutMapping(value="/Formations/{id}")
    public ResponseEntity<Formation> updateFormation(@PathVariable("id") long id, @RequestBody Formation formation) {
        try {
            Optional<Formation> formationData = formationRepository.findById(id);
            if(formationData.isPresent()){
                return new ResponseEntity<>(formationRepository.save(formation), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression d'un Formation
     * @param id
     * @return
     */
    @DeleteMapping(value="/formations/{id}")
    public ResponseEntity<List<Formation>> deleteFormationById(@PathVariable("id") long id) {
        try {
            formationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression de tous les Formations
     * @return
     */
    @DeleteMapping(value="/formations")
    public ResponseEntity<List<Formation>> deleteAllFormation() {
        try {
            formationRepository.deleteAll();;
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}