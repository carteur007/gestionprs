package com.carteur.gestionprs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.comptes.Compte;
import com.carteur.gestionprs.repositories.CompteRepository;

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
public class CompteController {
    
    @Autowired
    CompteRepository compteRepository;
    /**
     * Recherche les Comptes
     * @return
     */
    @GetMapping("/comptes")
    public ResponseEntity<List<Compte>>  getComptes() {
        try {
            List<Compte>  compteList = new ArrayList<Compte>(); 
            compteRepository.findAll().forEach(compteList::add);  
            if(compteList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            return new ResponseEntity<>(compteList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Compte>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher d'un Compte par son id
     * @param id
     * @return
     */
    @GetMapping("/comptes/{id}")
	public ResponseEntity<Compte> getUserById(@PathVariable("id") long id) {
        Optional<Compte> userData = compteRepository.findById(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    /**
     * Creation d'un Compte
     * @param Compte
     * @return
     */
    @PostMapping(value="/comptes")
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte ) {
        try {
            Compte _compte = compteRepository.save(compte);
            return new ResponseEntity<>(_compte, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Mise a jour d'un Compte
     * @param id
     * @param compte
     * @return
     */
    @PutMapping(value="/comptes/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable("id") long id, @RequestBody Compte compte) {
        try {
            Optional<Compte> compteData = compteRepository.findById(id);
            if(compteData.isPresent()){
                return new ResponseEntity<>(compteRepository.save(compte), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression d'un Compte
     * @param id
     * @return
     */
    @DeleteMapping(value="/comptes/{id}")
    public ResponseEntity<List<Compte>> deleteCompteById(@PathVariable("id") long id) {
        try {
            compteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression de tous les Comptes
     * @return
     */
    @DeleteMapping(value="/comptes")
    public ResponseEntity<List<Compte>> deleteAllCompte() {
        try {
            compteRepository.deleteAll();;
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}