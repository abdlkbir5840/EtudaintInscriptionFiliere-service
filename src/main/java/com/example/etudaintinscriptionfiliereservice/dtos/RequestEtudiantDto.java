package com.example.etudaintinscriptionfiliereservice.dtos;

import lombok.Data;

@Data
public class RequestEtudiantDto {
    private String id;
    private Long apogee;
    private String nom;
    private String prenom;
    private Long tel;
    private String ville;
    private String adress;
}