package com.carteur.gestionprs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.missions.Mision;
import com.carteur.gestionprs.missions.MisionsUser;
import com.carteur.gestionprs.repositories.MisionRepository;
import com.carteur.gestionprs.repositories.MisionUserRepository;
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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class MisionUserController {
    
    private final MisionRepository misionRepository;
    private final UserRepository userRepository;
    private final MisionUserRepository misionUserRepository;
    
    @Autowired
    public MisionUserController(
        UserRepository uRepo, 
        MisionRepository mRepo,
        MisionUserRepository muRepo) {
        this.userRepository = uRepo;
        this.misionRepository = mRepo;
        this.misionUserRepository = muRepo;
    }
    /**
     * Recherche les MissionUsers
     * @return
     */
    @GetMapping("/musers")
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
    @GetMapping("/musers/{id}")
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
    @PostMapping(value="/musers/{userId}/{misionId}/create")
    public ResponseEntity<MisionsUser> createMisionUser(
        @RequestBody MisionsUser misionUser ,
        @PathVariable("userId") long userId,
        @PathVariable("misionId") long misionId
        ) {
        try {
            Optional <User> _user = userRepository.findById(userId);
            Optional <Mision> _mision = misionRepository.findById(misionId);
            if(!(_user.isPresent()) && !(_mision.isPresent())){
                return ResponseEntity.notFound().build();
            }
            misionUser.setUser(_user.get());
            misionUser.setMission(_mision.get());
            MisionsUser _mision_user = misionUserRepository.save(misionUser);
            return new ResponseEntity<>(_mision_user, HttpStatus.CREATED);
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
    @PutMapping(value="/musers/{id}")
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
    @DeleteMapping(value="/musers/{id}")
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
    @DeleteMapping(value="/musers")
    public ResponseEntity<List<MisionsUser>> deleteAllMisionUser() {
        try {
            misionUserRepository.deleteAll();;
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

}