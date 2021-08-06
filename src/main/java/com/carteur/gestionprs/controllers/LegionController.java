package com.carteur.gestionprs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.legions.Legion;
import com.carteur.gestionprs.repositories.LegionRepository;

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
public class LegionController {
    
    @Autowired
    LegionRepository legionRepository;

    /**
     * Recherche les legions
     * @return
     */
    @GetMapping("/legions")
    public ResponseEntity<List<Legion>>  getLegions() {
        try {
            List<Legion> legionList = new ArrayList<Legion>(); 
            legionRepository.findAll().forEach(legionList::add);   
            if(legionList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            return new ResponseEntity<>(legionList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher d'une Legion par son id
     * @param id
     * @return
     */
    @GetMapping("/legions/{id}")
	public ResponseEntity<Legion> getLegionById(@PathVariable("id") long id) {
        Optional<Legion> legionData = legionRepository.findById(id);

		if (legionData.isPresent()) {
			return new ResponseEntity<>(legionData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    /**
     * Creation d'une Legion
     * @param legion
     * @return
     */
    @PostMapping(value="/legions")
    public ResponseEntity<Legion> createLegion(@RequestBody Legion legion ) {
        try {
            Legion _legion = legionRepository.save(legion);
            return new ResponseEntity<>(_legion, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Mise a jour d'une Legion
     * @param id
     * @param legion
     * @return
     */
    @PutMapping(value="/legions/{id}")
    public ResponseEntity<Legion> updateLegion(@PathVariable("id") long id, @RequestBody Legion legion) {
        try {
            Optional<Legion> legionData = legionRepository.findById(id);
            if(legionData.isPresent()){
                return new ResponseEntity<>(legionRepository.save(legion), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression d'une Legion
     * @param id
     * @return
     */
    @DeleteMapping(value="/legions/{id}")
    public ResponseEntity<List<Legion>> deleteLegionById(@PathVariable("id") long id) {
        try {
            legionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression de toutes les legions
     * @return
     */
    @DeleteMapping(value="/legions")
    public ResponseEntity<List<Legion>> deleteAllLegion() {
        try {
            legionRepository.deleteAll();;
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}