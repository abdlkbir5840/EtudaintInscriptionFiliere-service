package com.example.etudaintinscriptionfiliereservice.services;


import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDTo;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDTO;
import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.entities.Filiere;
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
    public  List<ResponseEtudiantDTO> getAllEtudiants(){
        return etudiantMapper.fromModel(etudiantRepository.findAll());
    }


    @Override
    public ResponseEtudiantDTO getEtudiantById(String idEtudiant) throws EntityNotFoundException {
        Etudiant etudiant=etudiantRepository.findById(idEtudiant).orElseThrow(()->new EntityNotFoundException("L' ID: "+idEtudiant+"n'existe pas"));
        return etudiantMapper.fromModel(etudiant);
   }
    //prob re voire
    @Override
    public ResponseEtudiantDTO getEtudiantByApogee(long apogee) throws EntityNotFoundException{
        Optional<Etudiant> etudiant = Optional.ofNullable(etudiantRepository.findByApogee(apogee));
        if(!etudiant.isPresent()) throw new EntityNotFoundException("L' APOGEE: "+apogee+"n'existe pas");
        return etudiantMapper.fromModel(etudiant.get());
    }

    @Override
    public ResponseEtudiantDTO addEtudiant(RequestEtudiantDTo requestEtudiantDTo) throws EntityAlreadyExistException , InvalidEntityException {
        if(requestEtudiantDTo.equals(null))
            throw new InvalidEntityException("L'etudiant n'existe pas");
        Optional<Etudiant> etudiant = Optional.ofNullable(etudiantRepository.findByApogee(requestEtudiantDTo.getApogee()));
        if(etudiant.isPresent()) throw new EntityAlreadyExistException(" L' APOGEE: "+requestEtudiantDTo.getApogee()+"existe deja");
        requestEtudiantDTo.setApogee(UUID.randomUUID().toString());
        return etudiantMapper.fromModel(etudiantRepository.save(etudiantMapper.toModel(requestEtudiantDTo)));
    }

    @Override
    public ResponseEtudiantDTO updateEtudiant(RequestEtudiantDTo requestEtudiantDTo) throws InvalidEntityException {
        if(requestEtudiantDTo.equals(null)) throw new InvalidEntityException("**");
        return etudiantMapper.fromModel(etudiantRepository.save(etudiantMapper.toModel(requestEtudiantDTo)));
    }

    @Override
    public void deleteEtudiant(long etudiantId) throws EntityNotFoundException {
        if(!etudiantRepository.findById(etudiantId).isPresent()) throw new EntityNotFoundException("l' etudiant du ID "+etudiantId+" n'existe pas");
        etudiantRepository.deleteById(etudiantId);
    }


       /* private EtudiantRepository etudiantRepository;

        public List<Etudiant> getAllEtudiants() {
            return etudiantRepository.findAll();
        }

        public Etudiant getEtudiantById(long id) throws EntityNotFoundException {
            return etudiantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Etudiant non trouvé"));
        }

        public Etudiant addEtudiant(RequestEtudiantDTo requestEtudiantDTo) {
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(requestEtudiantDTo.getNom());
            etudiant.setPrenom(requestEtudiantDTo.getPrenom());
            etudiant.setApogee(requestEtudiantDTo.getApogee());
            return etudiantRepository.save(etudiant);
        }

    @Override
    public ResponseEtudiantDTO UpdateEtudiant(RequestEtudiantDTo requestEtudiantDTo) {
        return null;
    }

    public Etudiant updateEtudiant(RequestEtudiantDTo requestEtudiantDTo) throws EntityNotFoundException {
            Etudiant etudiant = etudiantRepository.findById(requestEtudiantDTo.getId()).orElseThrow(() -> new EntityNotFoundException("Etudiant non trouvé"));
            etudiant.setNom(requestEtudiantDTo.getNom());
            etudiant.setPrenom(requestEtudiantDTo.getPrenom());
            etudiant.setApogee(requestEtudiantDTo.getApogee());
            return etudiantRepository.save(etudiant);
        }

        public ResponseEtudiantDTO getEtudiantByApogee(long apogee) {
            return etudiantRepository.findByApogee(apogee);
        }

        public void deleteEtudiant(long etudiantId) throws EntityNotFoundException {
            if (etudiantRepository.existsById(etudiantId)) {
                etudiantRepository.deleteById(etudiantId);
            } else {
                throw new EntityNotFoundException("Etudiant non trouvé");
            }
        }*/
    }












