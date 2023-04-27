package com.example.etudaintinscriptionfiliereservice.dtos;

import lombok.Data;

@Data
public class ResponseEtudiantDTO {

    private String id;
    private String apogee;
    private String firstname;
    private String lastname;
    private Long tel;
    private String ville;
    private String adress;
}