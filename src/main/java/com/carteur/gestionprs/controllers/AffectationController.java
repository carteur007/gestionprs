package com.carteur.gestionprs.controllers;

import com.carteur.gestionprs.affectations.Affectation;
import com.carteur.gestionprs.comptes.Compte;
import com.carteur.gestionprs.groupements.Groupement;
import com.carteur.gestionprs.repositories.CompteRepository;
import com.carteur.gestionprs.users.User;
import com.carteur.gestionprs.repositories.AffectationRepository;
import com.carteur.gestionprs.repositories.GroupementRepository;
import com.carteur.gestionprs.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;
import java.net.URI;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AffectationController {

    private final UserRepository userRepository;
    private final GroupementRepository groupementRepository;
    private final AffectationRepository affectationRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    public AffectationController(
        UserRepository userRepository, 
        GroupementRepository groupementRepository,
        AffectationRepository affectationRepository
    ){
        this.userRepository = userRepository;
        this.groupementRepository = groupementRepository;
        this.affectationRepository = affectationRepository;
    }
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
            return new ResponseEntity<>((List<Affectation>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Recherche les affectations
     * @return
     */
    @GetMapping("/affectations/compte/{id}")
    public ResponseEntity<List<Affectation>>  getAllAffectationsForAccount(@PathVariable Long id) {
        try {
            List<Affectation> affecList = new ArrayList<Affectation>();
            affectationRepository.findAllByCompte_Id(id).forEach(affecList::add);
            if(affecList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(affecList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Affectation>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Recherche les affectations
     * @return
     */
    @GetMapping("/affectations/groupement/{id}")
    public ResponseEntity<List<Affectation>>  getAllAffectationsForGroupement(@PathVariable Long id) {
        try {
            List<Affectation> affecList = new ArrayList<Affectation>();
            affectationRepository.findAllByGroupement_Id(id).forEach(affecList::add);
            if(affecList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(affecList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Affectation>) null, HttpStatus.INTERNAL_SERVER_ERROR);
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
		if (!affectationData.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
		return new ResponseEntity<>(affectationData.get(), HttpStatus.OK);
    }
    /**
     * Creation d'une affectation
     * @param affect
     * @return
     */
    @PostMapping(value="/affectations/{compteId}/{groupementId}/create")
    public ResponseEntity<Affectation> createAffectation(
            @RequestBody Affectation affect, 
            @PathVariable Long compteId,
            @PathVariable Long groupementId
            ) {
        try {
            Optional<Compte> compte = compteRepository.findById(compteId);
            Optional<Groupement>  _groupement = groupementRepository.findById(groupementId);
            if(!compte.isPresent() && !_groupement.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
            }
            affect.setCompte(compte.get());
            affect.setGroupement(_groupement.get());
            Affectation _affect = affectationRepository.save(affect);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{[id}")
            .buildAndExpand(_affect.getId()).toUri();
            return ResponseEntity.created(location).body(_affect);
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
                return ResponseEntity.unprocessableEntity().build();
            }
            affect.setId(affectData.get().getId());
            return new ResponseEntity<>(affectationRepository.save(affect), HttpStatus.OK);
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