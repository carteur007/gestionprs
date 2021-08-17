package com.carteur.gestionprs.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.carteur.gestionprs.repositories.UserRepository;
import com.carteur.gestionprs.users.User;

import com.carteur.gestionprs.users.UserHelper;
import com.carteur.gestionprs.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Liste des utilisateurs
     *
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>>  getUsers() {
        try {
            List<User> users = userService.findAll();
			System.out.println(users);
            if (users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
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
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userData = userService.getOne(id);

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
    @PostMapping(value = "/users", consumes = {"multipart/form-data"})
	public ResponseEntity<User> createUser(@ModelAttribute UserHelper user) {
		try {
            User _user = userService.save(user);

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
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @ModelAttribute UserHelper user) throws IOException {
		Optional<User> userData = userService.getOne(id);

		if (userData.isPresent()) {

			return new ResponseEntity<>(userService.update(user,id), HttpStatus.OK);
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
			userService.deleteAll();
			return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    @DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteOneUser(@PathVariable("id") Long id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}