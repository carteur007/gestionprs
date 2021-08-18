package com.carteur.gestionprs.users;

import com.carteur.gestionprs.affectations.Affectation;
import com.carteur.gestionprs.comptes.Compte;
import com.carteur.gestionprs.groupements.Groupement;
import com.carteur.gestionprs.legions.Legion;
import com.carteur.gestionprs.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Value("${file.upload-dir}")
    private String location ;
    @Value("${back-end-base}")
    private String backeEndBase;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private LegionRepository legionRepository;

    @Autowired
    private GroupementRepository groupementRepository;

    @Autowired
    private AffectationRepository affectationRepository;


    //Save one user
    public User save(UserHelper userHelper) throws IOException {

        User user = new User();
        Compte compte = new Compte();
        Affectation affectation = new Affectation();
        Groupement groupement = groupementRepository.findByNom(userHelper.getGroupement());
        Legion legion = groupement.getLegion();

        affectation.setCode("AF/"+userRepository.findAll().size()+"/"+legion.getCode()+"/"+groupement.getCode());
        affectation.setGroupement(groupement);
        affectation.setName(userHelper.getFonction());
        affectation.setVille(userHelper.getVille());
        affectation.setStatus(true);
        compte.setFonction(userHelper.getFonction());
        compte.setGrade(userHelper.getGrade());
        compte.setGroupement(userHelper.getGroupement());
        compte.setLegion(userHelper.getLegion());
        compte.setMatricule(userHelper.getMatricule());
        compte.setMotPasse(userHelper.getMatricule());
        compte.setQuartier(userHelper.getQuartier());
        compte.setTelephone(userHelper.getTelephone());
        user.setArrondissement(userHelper.getArrondissement());
        user.setCentreFormation(userHelper.getCentreFormation());
        user.setEmail(userHelper.getEmail());
        user.setLieuNaissance(userHelper.getLieuNaissance());
        user.setMatricule(userHelper.getMatricule());
        user.setNom(userHelper.getNom());
        user.setPrenom(userHelper.getPrenom());
        user.setNomMere(userHelper.getNomMere());
        user.setNomPere(userHelper.getNomPere());
        user.setRegion(userHelper.getRegion());
        user.setTelephone(userHelper.getTelephone());
        user.setDateEntree(userHelper.getDateEntree());
        user.setDateNaissance(userHelper.getDateNaissance());
        if (!(userHelper.getFile().isEmpty())){
            Path path = Paths.get(location + "/" + userHelper.getFile().getOriginalFilename());
            byte[] bytes = userHelper.getFile().getBytes();

            Files.write(path, bytes);
            user.setProfile(backeEndBase + "/uploadFiles/" + userHelper.getFile().getOriginalFilename());
        }
        compte.setPhoto(user.getProfile());

       User savedUser = userRepository.save(user);
       compte.setUser(savedUser);
       compteRepository.save(compte);
       affectation.setCompte(compte);
       affectationRepository.save(affectation);
       return savedUser;

    }

    // Get user list
    public List<User> findAll(){
        return userRepository.findAll(Sort.by(Sort.Direction.DESC,"nom"));
    }

    // Get user by id
    public Optional<User> getOne(Long id){
        return userRepository.findById(id);
    }

    // Update user
    public User update(UserHelper userHelper, Long id) throws IOException {
        User user = userRepository.getById(id);
        user.setArrondissement(userHelper.getArrondissement());
        user.setCentreFormation(userHelper.getCentreFormation());
        user.setEmail(userHelper.getEmail());
        user.setLieuNaissance(userHelper.getLieuNaissance());
        user.setMatricule(userHelper.getMatricule());
        user.setNom(userHelper.getNom());
        user.setPrenom(userHelper.getPrenom());
        user.setNomMere(userHelper.getNomMere());
        user.setNomPere(userHelper.getNomPere());
        user.setRegion(userHelper.getRegion());
        user.setTelephone(userHelper.getTelephone());
        user.setDateEntree(userHelper.getDateEntree());
        user.setDateNaissance(userHelper.getDateNaissance());
        if (!(userHelper.getFile().isEmpty())){
            Path path = Paths.get(location + "/" + userHelper.getFile().getOriginalFilename());
            byte[] bytes = userHelper.getFile().getBytes();

            Files.write(path, bytes);
            user.setProfile(backeEndBase + "/uploadFiles/" + userHelper.getFile().getOriginalFilename());
        }

        return userRepository.save(user);

    }

    // Delete all users

    public void deleteAll(){
        userRepository.deleteAll();
    }

    // Delete user by id
    public void  delete(Long id){
        userRepository.deleteById(id);
    }
}
