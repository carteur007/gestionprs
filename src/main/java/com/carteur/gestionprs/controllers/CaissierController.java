package com.carteur.gestionprs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.caissiers.Caissier;
import com.carteur.gestionprs.repositories.CaissierRepository;

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
public class CaissierController {

    @Autowired
    CaissierRepository caissierRepository;

    /**
     * Recherche les caissiers
     * @return
     */
    @GetMapping("/caissiers")
    public ResponseEntity<List<Caissier>>  getCaissiers() {
        try {
            List<Caissier>  caissierList = new ArrayList<Caissier>(); 
            caissierRepository.findAll().forEach(caissierList::add);  
            if(caissierList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            return new ResponseEntity<>(caissierList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Caissier>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher d'un caissier par son id
     * @param id
     * @return
     */
    @GetMapping("/caissiers/{id}")
	public ResponseEntity<Caissier> getUserById(@PathVariable("id") long id) {
        Optional<Caissier> userData = caissierRepository.findById(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    /**
     * Creation d'un caissier
     * @param caissier
     * @return
     */
    @PostMapping(value="/caissiers")
    public ResponseEntity<Caissier> createCaissier(@RequestBody Caissier caissier ) {
        try {
            Caissier _caissier = caissierRepository.save(caissier);
            return new ResponseEntity<>(_caissier, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Mise a jour d'un caissier
     * @param id
     * @param caissier
     * @return
     */
    @PutMapping(value="/caissiers/{id}")
    public ResponseEntity<Caissier> updateCaissier(@PathVariable("id") long id, @RequestBody Caissier caissier) {
        try {
            Optional<Caissier> caisseData = caissierRepository.findById(id);
            if(caisseData.isPresent()){
                return new ResponseEntity<>(caissierRepository.save(caissier), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression d'un caissier
     * @param id
     * @return
     */
    @DeleteMapping(value="/caissiers/{id}")
    public ResponseEntity<List<Caissier>> deleteCaissierById(@PathVariable("id") long id) {
        try {
            caissierRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression de tous les caissiers
     * @return
     */
    @DeleteMapping(value="/caissiers")
    public ResponseEntity<List<Caissier>> deleteAllCaissier() {
        try {
            caissierRepository.deleteAll();;
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

}