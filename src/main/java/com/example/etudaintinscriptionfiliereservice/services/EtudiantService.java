package com.example.etudaintinscriptionfiliereservice.services;


import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EtudiantService implements EtudiantServiceInterface {

    @Autowired
    private EtudiantRepository etudiantRepository;


    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }


    public List<Etudiant> listerEtudiants() {
        return etudiantRepository.findAll();
    }

    /*public Consulter<Etudiant> chercherEtudiant(Long id) {
        return etudiantRepository.findById(id);
    }
    */


    public void supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }


    public Etudiant modifierEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }
}

