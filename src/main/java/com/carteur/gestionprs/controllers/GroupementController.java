package com.carteur.gestionprs.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.legions.Legion;
import com.carteur.gestionprs.groupements.Groupement;
import com.carteur.gestionprs.repositories.LegionRepository;
import com.carteur.gestionprs.repositories.GroupementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class GroupementController {
    
    private final LegionRepository legionRepository;
    private final GroupementRepository groupementRepository;

    @Autowired
    public GroupementController(LegionRepository legionRepository, GroupementRepository groupementRepository) {
        this.legionRepository = legionRepository;
        this.groupementRepository = groupementRepository;
    }
    /**
     * Recherche les groupements
     * 
     * @return
     */
    @GetMapping("/groupements")
    public ResponseEntity<List<Groupement>> getGroupements() {
        try {
            List<Groupement>groupementList = new ArrayList<Groupement>();
            groupementRepository.findAll().forEach(groupementList::add);
            if (groupementList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(groupementList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Groupement>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher d'un Groupement par son id
     * 
     * @param id
     * @return
     */
    @GetMapping("/groupements/{id}")
    public ResponseEntity<Groupement> getLegionById(@PathVariable("id") long id) {
        Optional<Groupement> groupementData = groupementRepository.findById(id);
        if (!groupementData.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return new ResponseEntity<>(groupementData.get(),HttpStatus.OK);
    }
    /**
     * Recherche d'un Groupement par l'id de la legion
     * @param id
     *
     * @return
     */
    @GetMapping("/groupements/legion/{id}")
    public ResponseEntity<List<Groupement>> getByLegionId(@PathVariable Long id) {
        return ResponseEntity.ok(groupementRepository.findAllByLegion_Id(id));
    }
    /**
     * Creation d'un Groupement
     * 
     * @param groupement
     * @return
     */
    @PostMapping(value = "/groupements/{legionId}/create")
    public ResponseEntity<Groupement> createGroupement( @PathVariable("legionId")long legionId ,@RequestBody Groupement groupement) {
        try {
            Optional<Legion> optionalLegion = legionRepository.findById(legionId);
            if (!optionalLegion.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            groupement.setLegion(optionalLegion.get());
            Groupement _groupement = groupementRepository.save(groupement);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(_groupement.getId()).toUri();
            return ResponseEntity.created(location).body(_groupement);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Mise a jour d'un Groupement
     * 
     * @param id
     * @param
     * @return
     */
    @PutMapping(value = "/groupements/{id}")
    public ResponseEntity<Groupement> updateGroupement(@PathVariable("id") long id, @RequestBody Groupement groupement) {
        try {
            Optional<Groupement> groupementData = groupementRepository.findById(id);
            Optional<Legion> optionalLegion = legionRepository.findById(groupementData.get().getLegion().getId());
            if (!optionalLegion.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            if (!groupementData.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            groupement.setLegion(optionalLegion.get());
            groupement.setId(groupementData.get().getId());
            return new ResponseEntity<>(groupementRepository.save(groupement), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Suppression d'un Groupement
     * 
     * @param id
     * @return
     */
    @DeleteMapping(value = "/groupements/{id}")
    public ResponseEntity<List<Groupement>> deleteGroupementById(@PathVariable("id") long id) {
        try {
            Optional<Groupement> groupementData = groupementRepository.findById(id);
            if (!groupementData.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            groupementRepository.delete(groupementData.get());
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Suppression de tous les groupements
     * 
     * @return
     */
    @DeleteMapping(value = "/groupements")
    public ResponseEntity<List<Groupement>> deleteAllGroupement() {
        try {
            groupementRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}