package com.example.etudaintinscriptionfiliereservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(unique = true)
    private String apogee;
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
    private Long tel;
    private String ville;
    private String adress;
    @OneToOne(mappedBy = "etudiant")
    private Inscription inscriptions;

}