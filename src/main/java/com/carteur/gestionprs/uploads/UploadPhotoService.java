package com.carteur.gestionprs.uploads;

import java.io.IOException;
import java.util.Optional;

import com.carteur.gestionprs.comptes.Compte;
import com.carteur.gestionprs.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadPhotoService {
    
    private CompteRepository compteRepository;

    @Autowired
    public  UploadPhotoService(CompteRepository repository) {
        this.compteRepository = repository;
    }
    
    public void addPhoto(long compteId, MultipartFile mFile) throws IOException {
        Optional<Compte> compteData = compteRepository.findById(compteId);
        Compte _compte = compteData.get();
        //_compte.setPhoto(mFile.getBytes());
        compteRepository.save(_compte);
    }
    public byte[]  getPhoto(long compteId)throws IOException {
        Optional<Compte> compteData = compteRepository.findById(compteId);
        Compte _compte = compteData.get();
        return null;
    }
}