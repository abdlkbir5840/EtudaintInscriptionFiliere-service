package com.example.etudaintinscriptionfiliereservice.dtos;

import lombok.Data;

@Data
public class ResponseEtudiantDto {

    private String id;
    private Long apogee;
    private String firstname;
    private String lastname;
    private Long tel;
    private String ville;
    private String adress;
}