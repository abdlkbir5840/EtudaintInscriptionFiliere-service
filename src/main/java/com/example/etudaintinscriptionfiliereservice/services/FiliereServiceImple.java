package com.example.etudaintinscriptionfiliereservice.services.implement;

import com.example.etudaintinscriptionfiliereservice.dtos.FiliereRequestDto;
import com.example.etudaintinscriptionfiliereservice.dtos.FiliereResponseDto;
import com.example.etudaintinscriptionfiliereservice.entities.Filiere;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityAlreadyExistException;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.exceptions.InvalidEntityException;
import com.example.etudaintinscriptionfiliereservice.mappers.FiliereMapper;
import com.example.etudaintinscriptionfiliereservice.repositories.FiliereRepository;
import com.example.etudaintinscriptionfiliereservice.services.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FiliereServiceImple implements FiliereService {
    private final FiliereRepository filiereRepository;
    private final FiliereMapper filiereMapper;
    @Autowired
    public FiliereServiceImple(FiliereRepository filiereRepository, FiliereMapper filiereMapper) {
        this.filiereRepository = filiereRepository;
        this.filiereMapper = filiereMapper;
    }

    @Override
    public List<FiliereResponseDto> getAll() {
        return filiereMapper.fromModels(filiereRepository.findAll());
    }

    @Override
    public FiliereResponseDto getFiliereById(String idFiliere) throws EntityNotFoundException {
        Filiere filiere = filiereRepository.findById(idFiliere)
                .orElseThrow(()->new EntityNotFoundException("No filier with ID "+idFiliere+" were found"));
        return filiereMapper.fromModel(filiere);
    }

    @Override
    public FiliereResponseDto getFiliereByNmae(String name) throws EntityNotFoundException, MethodArgumentNotValidException {
        Optional<Filiere> filiere = Optional.ofNullable(filiereRepository.findByName(name));
        if(!filiere.isPresent()) throw new EntityNotFoundException("No filier with ID "+name+" were found");
        return filiereMapper.fromModel(filiere.get());
    }

    @Override
    public FiliereResponseDto save(FiliereRequestDto filiereRequestDto) throws EntityAlreadyExistException {
        if (filiereRequestDto.equals(null))throw new InvalidEntityException("Filiere Not Valid");
        Optional<Filiere> filiere = Optional.ofNullable(filiereRepository.findByName(filiereRequestDto.getName()));
        if(filiere.isPresent()) throw new EntityAlreadyExistException("Article wiht code "+filiereRequestDto.getName()+" allready exist");
        filiereRequestDto.setIdFiliere(UUID.randomUUID().toString());
        return filiereMapper.fromModel(filiereRepository.save(filiereMapper.toModel(filiereRequestDto)));
    }

    @Override
    public FiliereResponseDto update(FiliereRequestDto filiereRequestDto) {
        if (filiereRequestDto.equals(null))throw new InvalidEntityException("Filiere Not Valid");
        return filiereMapper.fromModel(filiereRepository.save(filiereMapper.toModel(filiereRequestDto)));
    }

    @Override
    public void delete(String id) throws EntityNotFoundException {

    }
}
