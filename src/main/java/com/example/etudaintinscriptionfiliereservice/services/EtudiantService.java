package com.example.etudaintinscriptionfiliereservice.services;

import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDTo;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDTO;
import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;

import java.util.List;

public interface EtudiantService {


    List<ResponseEtudiantDTO> getAllEtudiants();
    ResponseEtudiantDTO getEtudiantById(String id) throws EntityNotFoundException ;
    ResponseEtudiantDTO getEtudiantByApogee(String apogee);
    ResponseEtudiantDTO addEtudiant(RequestEtudiantDTo requestEtudiantDTo)throws EntityAlreadyExistException;
    ResponseEtudiantDTO updateEtudiant(RequestEtudiantDTo requestEtudiantDTo);
    void  deleteEtudiant(long etudiantId)throws EntityNotFoundException;

}

