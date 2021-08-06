package com.carteur.gestionprs.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.repositories.UserRepository;
import com.carteur.gestionprs.users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

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
            return new ResponseEntity<>((List<User>) null, HttpStatus.INTERNAL_SERVER_ERROR);
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

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
            /*
			User _user = userRepository.save(new User(
                user.getNom(), user.getPrenom(), user.getNomMere(),
                user.getNomPere(), user.getEmail(),user.getMatricule(), user.getTelephone(),
                user.getLieuNaissance(), user.getDateNaissance(), user.getDateEntree(),
                user.getCentreFormation(), user.getRegion(),user.getArrondissement()));
                */
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>((User) null, HttpStatus.INTERNAL_SERVER_ERROR);
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

		if (userData.isPresent()) {
            /*
			User _user = new User();
			_user.setNom(user.getNom());
			_user.setPrenom(user.getPrenom());
			_user.setNomMere(user.getNomMere());
            _user.setNomPere(user.getNomPere());
            _user.setMatricule(user.getMatricule());
            _user.setRegion(user.getRegion());
            _user.setEmail(user.getEmail());
            _user.setArrondissement(user.getArrondissement());
            _user.setCentreFormation(user.getCentreFormation());
            _user.setDateEntree(user.getDateEntree());
            _user.setDateNaissance(user.getDateNaissance());
            _user.setTelephone(user.getTelephone());
            */
			return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
    @DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteOneUser(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    /*
    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }*/
}