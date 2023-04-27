package com.example.etudaintinscriptionfiliereservice.mappers;

import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDTo;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDTO;

import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EtudiantMapper {
    ResponseEtudiantDTO fromModel(Etudiant etudiant );
    List<ResponseEtudiantDTO> fromModel(List<Etudiant> etudiant );
    Etudiant toModel(RequestEtudiantDTo requestEtudiantDTo);
}
