package com.example.etudaintinscriptionfiliereservice.services;


import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDto;
import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.exceptions.InvalidEntityException;
import com.example.etudaintinscriptionfiliereservice.mappers.EtudiantMapper;
import com.example.etudaintinscriptionfiliereservice.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final EtudiantMapper etudiantMapper;

    @Autowired
    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, EtudiantMapper etudiantMapper) {
        this.etudiantRepository = etudiantRepository;
        this.etudiantMapper = etudiantMapper;
    }
    @Override
    public  List<ResponseEtudiantDto> getAllEtudiants(){
        return etudiantMapper.fromModel(etudiantRepository.findAll());
    }

    //Verifier
    @Override
    public ResponseEtudiantDto getEtudiantById(String idEtudiant) throws EntityNotFoundException {
        Etudiant etudiant=etudiantRepository.findById(Long.valueOf(idEtudiant)).orElseThrow(()->new EntityNotFoundException("L' ID: "+idEtudiant+"n'existe pas"));
        return etudiantMapper.fromModel(etudiant);
   }
    //prob re voire
    @Override
    public ResponseEtudiantDto getEtudiantByApogee(String apogee) throws EntityNotFoundException{
        Optional<Etudiant> etudiant = Optional.ofNullable(etudiantRepository.findByApogee(apogee));
        if(!etudiant.isPresent()) throw new EntityNotFoundException("L' APOGEE: "+apogee+"n'existe pas");
        return etudiantMapper.fromModel(etudiant.get());
    }

    @Override
    public ResponseEtudiantDto addEtudiant(RequestEtudiantDto requestEtudiantDTo) throws EntityAlreadyExistException , InvalidEntityException {
        if(requestEtudiantDTo.equals(null))
            throw new InvalidEntityException("L'etudiant n'existe pas");
        Optional<Etudiant> etudiant = Optional.ofNullable(etudiantRepository.findByApogee(requestEtudiantDTo.getApogee()));
        if(etudiant.isPresent()) throw new EntityAlreadyExistException(" L' APOGEE: "+requestEtudiantDTo.getApogee()+"existe deja");
        requestEtudiantDTo.setApogee(UUID.randomUUID().toString());
        return etudiantMapper.fromModel(etudiantRepository.save(etudiantMapper.toModel(requestEtudiantDTo)));
    }

    @Override
    public ResponseEtudiantDto updateEtudiant(RequestEtudiantDto requestEtudiantDTo) throws InvalidEntityException {
        if(requestEtudiantDTo.equals(null)) throw new InvalidEntityException("**");
        return etudiantMapper.fromModel(etudiantRepository.save(etudiantMapper.toModel(requestEtudiantDTo)));
    }

    @Override
    public void deleteEtudiant(String etudiantId) throws EntityNotFoundException {
        if(!etudiantRepository.findById(Long.valueOf(etudiantId)).isPresent()) throw new EntityNotFoundException("l' etudiant du ID "+etudiantId+" n'existe pas");
        etudiantRepository.deleteById(Long.valueOf(etudiantId));
    }

    }












