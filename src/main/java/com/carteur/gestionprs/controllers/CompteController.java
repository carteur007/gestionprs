package com.carteur.gestionprs.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.carteur.gestionprs.users.User;
import com.carteur.gestionprs.comptes.Compte;
import com.carteur.gestionprs.repositories.UserRepository;
import com.carteur.gestionprs.uploads.UploadPhotoService;
import com.carteur.gestionprs.repositories.CompteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class CompteController {
    
    private final UserRepository userRepository;
    private final CompteRepository compteRepository;
    private final UploadPhotoService photoService;

    @Autowired
    public CompteController(UploadPhotoService photoService,UserRepository userRepository, CompteRepository compteRepository) {
        this.photoService = photoService;
        this.userRepository = userRepository;
        this.compteRepository = compteRepository;
    }
    /**
     * Recherche les Comptes
     * @return
     */
    @GetMapping("/comptes")
    public ResponseEntity<List<Compte>>  getComptes() {
        try {
            List<Compte>  compteList = new ArrayList<Compte>(); 
            compteRepository.findAll().forEach(compteList::add);  
            if(compteList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            return new ResponseEntity<>(compteList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Compte>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Chercher un Compte par son id
     * @param id
     * @return
     */
    @GetMapping("/comptes/{id}")
	public ResponseEntity<Compte> getCompteById(@PathVariable("id") long id) {
        Optional<Compte> compteData = compteRepository.findById(id);
		if (compteData.isPresent()) {
			return new ResponseEntity<>(compteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    /**
     * Creation d'un Compte
     * @param Compte
     * @return
     */
    @PostMapping(value="/comptes/{userId}")
    public ResponseEntity<Compte> createCompte(@PathVariable("userId") long userId, @RequestBody Compte compte ) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            compte.setUser(optionalUser.get());
            return ResponseEntity.ok(compteRepository.save(compte));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Mise a jour d'un Compte
     * @param id
     * @param compte
     * @return
     */
    @PutMapping(value="/comptes/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable("id") long id, @RequestBody Compte compte) {
        try {
            Optional<Compte> compteData = compteRepository.findById(id);
            Optional<User> optionalUser = userRepository.findById(compteData.get().getUser().getId());
            if (!optionalUser.isPresent() || !compteData.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            compte.setUser(optionalUser.get());
            compte.setId(compteData.get().getId());
            Compte _compte = compteRepository.save(compte);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(_compte.getId()).toUri();
            return ResponseEntity.created(location).body(_compte);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression d'un Compte
     * @param id
     * @return
     */
    @DeleteMapping(value="/comptes/{id}")
    public ResponseEntity<List<Compte>> deleteCompteById(@PathVariable("id") long id) {
        try {
            Optional<Compte> compteData = compteRepository.findById(id);
            if (!compteData.isPresent()){
                return ResponseEntity.unprocessableEntity().build();
            }
            compteRepository.delete(compteData.get());
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Suppression de tous les Comptes
     * @return
     */
    @DeleteMapping(value="/comptes")
    public ResponseEntity<List<Compte>> deleteAllCompte() {
        try {
            compteRepository.deleteAll();;
            return new ResponseEntity<>(HttpStatus.GONE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    /**
     * Upload de photo
     * @param compteId
     * @param mFile
     * @return
     * @throws IOException
     */
    @PostMapping("/photo/{compteId}/comptes")
    public ResponseEntity<Void> addPhoto(@PathVariable("compteId")long compteId, @RequestParam("file") MultipartFile mFile)throws IOException{
        try {
            photoService.addPhoto(compteId, mFile);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/photo/${compteId}/comptes").buildAndExpand(compteId).toUri();
            return ResponseEntity.created(location).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * Recuperation de la photo
     * @param compteId
     * @return
     * @throws IOException
     */
    @GetMapping(value="/photo/{compteId}/comptes")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("compteId") long compteId ) throws IOException {
        byte[] photo = photoService.getPhoto(compteId);
        return  ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(MediaType.IMAGE_PNG_VALUE))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"${System.currentTimeMillis()}\"")
            .body(photo);
    }
}