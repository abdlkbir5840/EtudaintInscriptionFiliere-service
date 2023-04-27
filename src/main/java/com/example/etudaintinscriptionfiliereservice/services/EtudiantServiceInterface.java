package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;

import java.util.List;

public interface EtudiantServiceInterface {

    Etudiant ajouterEtudiant(Etudiant etudiant);

    List<Etudiant> listerEtudiants();

//    Consulter<Etudiant> chercherEtudiant(Long id);

    void supprimerEtudiant(Long id);

    Etudiant modifierEtudiant(Etudiant etudiant);
}

