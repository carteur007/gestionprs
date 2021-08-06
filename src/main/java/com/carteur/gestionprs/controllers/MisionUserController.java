package com.carteur.gestionprs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.missions.MisionsUser;
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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class MisionUserController {
    
    @Autowired
    MisionUserRepository misionUserRepository;

    /**
     * Recherche les MissionUsers
     * @return
     */
    @GetMapping("/missionusers")
    public ResponseEntity<List<MisionsUser>>  getMisionUsers() {
        try {
            List<MisionsUser> misionList = new ArrayList<MisionsUser>(); 
            misionUserRepository.findAll().forEach(misionList::add);   
            if(misionList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            return new ResponseEntity<>(misionList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<MisionsUser>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher d'une MisisonUser par son id
     * @param id
     * @return
     */
    @GetMapping("/missionusers/{id}")
	public ResponseEntity<MisionsUser> getMisionUserById(@PathVariable("id") long id) {
        Optional<MisionsUser> misionData = misionUserRepository.findById(id);

		if (misionData.isPresent()) {
			return new ResponseEntity<>(misionData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    /**
     * Creation d'une MissionUser
     * @param mision
     * @return
     */
    @PostMapping(value="/missionusers")
    public ResponseEntity<MisionsUser> createMisionUser(@RequestBody MisionsUser misionUser ) {
        try {
            MisionsUser _mision = misionUserRepository.save(misionUser);
            return new ResponseEntity<>(_mision, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Mise a jour d'une MisionsUser
     * @param id
     * @param mision
     * @return
     */
    @PutMapping(value="/missionusers/{id}")
    public ResponseEntity<MisionsUser> updateMisionUser(@PathVariable("id") long id, @RequestBody MisionsUser misionUser) {
        try {
            Optional<MisionsUser> misionData = misionUserRepository.findById(id);
            if(misionData.isPresent()){
                return new ResponseEntity<>(misionUserRepository.save(misionUser), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression d'une MisionsUser
     * @param id
     * @return
     */
    @DeleteMapping(value="/missionusers/{id}")
    public ResponseEntity<List<MisionsUser>> deleteMisionUserById(@PathVariable("id") long id) {
        try {
            misionUserRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression de toutes les MisionUsers
     * @return
     */
    @DeleteMapping(value="/missionusers")
    public ResponseEntity<List<MisionsUser>> deleteAllMisionUser() {
        try {
            misionUserRepository.deleteAll();;
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

}