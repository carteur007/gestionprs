package com.carteur.gestionprs.controllers;

import java.net.URI;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import com.carteur.gestionprs.users.User;
import com.carteur.gestionprs.caissiers.Caissier;
import com.carteur.gestionprs.repositories.UserRepository;  
import com.carteur.gestionprs.repositories.CaissierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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
public class CaissierController {


    private final UserRepository userRepository;
    private final CaissierRepository caissierRepository;

    @Autowired
    public CaissierController(UserRepository userRepository, CaissierRepository caissierRepository) {
        this.userRepository = userRepository;
        this.caissierRepository = caissierRepository;
    }

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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
     * Recherche d'un caissier par l'id de l'utilisateur
     * @param userId
     * @param pageable
     * @return
     */
    @GetMapping("/caissiers/{userId}")
    public ResponseEntity<Page<Caissier>> getByUserId(@PathVariable long userId, Pageable pageable) {
        return ResponseEntity.ok(caissierRepository.findByUserId(userId, pageable));
    }
    /**
     * Creation d'un caissier
     * @param caissier
     * @return
     */
    @PostMapping(value="/caissiers")
    public ResponseEntity<Caissier> createCaissier(@RequestBody Caissier caissier ) {
        try {
            Optional<User> optionalUser = userRepository.findById(caissier.getUser().getId());
            if (!optionalUser.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            caissier.setUser(optionalUser.get());
            Caissier _caissier = caissierRepository.save(caissier);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(_caissier.getId()).toUri();
            return ResponseEntity.created(location).body(_caissier);
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
            Optional<User> optionalUser = userRepository.findById(caisseData.get().getUser().getId());
            if (!optionalUser.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            if(!caisseData.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
            }
            caissier.setUser(optionalUser.get());
            caissier.setId(caisseData.get().getId());
            return new ResponseEntity<>(caissierRepository.save(caissier), HttpStatus.OK);
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
            Optional<Caissier> caisseData = caissierRepository.findById(id);
            if (!caisseData.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            caissierRepository.delete(caisseData.get());
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