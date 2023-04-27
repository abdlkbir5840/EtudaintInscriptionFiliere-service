package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDTo;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDTO;
import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;

import java.util.List;

public interface EtudiantService {


    List<Etudiant> getAllEtudiants();
    ResponseEtudiantDTO getEtudiantById(long id) throws EntityNotFoundException ;
    ResponseEtudiantDTO getEtudiantByApogee(long apogee);
    Etudiant addEtudiant(RequestEtudiantDTo requestEtudiantDTo)throws EntityAlreadyExistException;
    ResponseEtudiantDTO UpdateEtudiant(RequestEtudiantDTo requestEtudiantDTo);
    void  deleteEtudiant(long etudiantId)throws EntityNotFoundException;

}

