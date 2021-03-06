package com.carteur.gestionprs.controllers;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.missions.Mision;
import com.carteur.gestionprs.repositories.MisionRepository;
import com.carteur.gestionprs.repositories.MisionUserRepository;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class MisionController {
    
    private final MisionRepository misionRepository;
    private final MisionUserRepository misionUserRepository;

    @Autowired
    public MisionController(MisionRepository misionRepository, MisionUserRepository misionUserRepository) {
        this.misionRepository = misionRepository;
        this.misionUserRepository = misionUserRepository;
    }
    /**
     * Recherche les Missions
     * @return
     */
    @GetMapping("/missions")
    public ResponseEntity<List<Mision>>  getMisions() {
        try {
            List<Mision> misionList = new ArrayList<Mision>(); 
            misionRepository.findAll().forEach(misionList::add);   
            if(misionList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            return new ResponseEntity<>(misionList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Mision>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher d'une Misison par son id
     * @param id
     * @return
     */
    @GetMapping("/missions/{id}")
	public ResponseEntity<Mision> getMisionById(@PathVariable("id") long id) {
        Optional<Mision> misionData = misionRepository.findById(id);
		if (misionData.isPresent()) {
			return new ResponseEntity<>(misionData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    /**
     * Creation d'une Mission
     * @param mision
     * @return
     */
    @PostMapping(value="/missions")
    public ResponseEntity<Mision> createMision(@RequestBody Mision mision ) {
        try {
            Mision _mision = misionRepository.save(mision);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(_mision.getId()).toUri();
            return ResponseEntity.created(location).body(_mision);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Mise a jour d'une Mision
     * @param id
     * @param mision
     * @return
     */
    @PutMapping(value="/missions/{id}")
    public ResponseEntity<Mision> updateMision(@PathVariable("id") long id, @RequestBody Mision mision) {
        try {
            Optional<Mision> misionData = misionRepository.findById(id);
            if(misionData.isPresent()){
                return new ResponseEntity<>(misionRepository.save(mision), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression de toutes les Misions
     * @return
     */
    @DeleteMapping(value="/missions")
    public ResponseEntity<List<Mision>> deleteAllMision() {
        try {
            misionRepository.deleteAll();;
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression complete d'une Mision
     * @param id
     * @return
     */
    @DeleteMapping(value="/missions/{id}")
    public ResponseEntity<List<Mision>> deleteMisionById(@PathVariable("id") long id) {
        try {
            Optional<Mision> misionData = misionRepository.findById(id);
            if (!misionData.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
		    } 
            deleteMisionUserInTransaction(misionData.get());
            misionRepository.delete(misionData.get());
            return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    @Transactional
    public void deleteMisionUserInTransaction(Mision mision) {
        misionUserRepository.deleteByMisionUserId(mision.getId());
    }
}