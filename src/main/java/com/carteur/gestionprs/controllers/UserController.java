package com.carteur.gestionprs.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.repositories.AffectationRepository;
import com.carteur.gestionprs.repositories.CaissierRepository;
import com.carteur.gestionprs.repositories.FormationRepository;
import com.carteur.gestionprs.repositories.GradeRepository;
import com.carteur.gestionprs.repositories.MisionUserRepository;
import com.carteur.gestionprs.repositories.UserRepository;
import com.carteur.gestionprs.users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;
    private final CaissierRepository caissierRepository;
    private final FormationRepository formationRepository;
    private final MisionUserRepository misionUserRepository;
    private final AffectationRepository affectationRepository;

    @Autowired
    public UserController(
        UserRepository userRepository, 
        GradeRepository gradeRepository,
        CaissierRepository caissierRepository,
        FormationRepository formationRepository,
        MisionUserRepository misionUserRepository,
        AffectationRepository affectationRepository
    ){
        this.userRepository = userRepository;
        this.gradeRepository = gradeRepository;
        this.caissierRepository = caissierRepository;
        this.formationRepository = formationRepository;
        this.misionUserRepository = misionUserRepository;
        this.affectationRepository = affectationRepository;
    }
    /**
     * Liste des utilisateurs soit un utilisateur en fonction de son matricule
     * @param matricule
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>>  getUsers(@RequestParam(required = false) String matricule) {
        try {
            List<User> users = new ArrayList<User>();
            if(matricule == null)
                userRepository.findAll().forEach(users::add);
            else
                userRepository.findByMatriculeContaining(matricule).forEach(users::add);
            if(users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher un utilisateur par son id
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);
        if (!userData.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
		} 
        return ResponseEntity.ok(userData.get());
    }
    /**
     * Creation de l'utilisateur
     * @param user
     * @return
     */
    @PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
            User _user = userRepository.save(user);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(_user.getId()).toUri();
            return ResponseEntity.created(location).body(_user);
		} catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
		}
	}
    /**
     * Mise a jour de l'utilisateur
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		Optional<User> userData = userRepository.findById(id);
		if (!userData.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
		} 
        user.setId(userData.get().getId());
        userRepository.save(user);
		return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
	}
    /**
     * Supprimer tous les utilisateurs
     * @return
     */
    @DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			userRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    /**
     * Suppression complet d'un utilisateur
     * @param id
     * @return
     */
    @DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteOneUser(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);
		if (!userData.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
		} 
        deleteGradeInTransaction(userData.get());
        deleteCaissierInTransaction(userData.get());
        deleteFormationInTransaction(userData.get());
        deleteMissionUserInTransaction(userData.get());
        deleteAffectationInTransaction(userData.get());
        userRepository.delete(userData.get());
        return ResponseEntity.noContent().build();
	}
    @Transactional
    public void deleteGradeInTransaction(User user) {
        gradeRepository.deleteByUserId(user.getId());
    }
    @Transactional
    public void deleteCaissierInTransaction(User user) {
        caissierRepository.deleteByUserId(user.getId());
    }
    @Transactional
    public void deleteFormationInTransaction(User user) {
        formationRepository.deleteByUserId(user.getId());
    }
    @Transactional
    public void deleteMissionUserInTransaction(User user) {
        misionUserRepository.deleteByUserId(user.getId());
    }
    @Transactional
    public void deleteAffectationInTransaction(User user) {
        affectationRepository.deleteByUserId(user.getId());
    }
}