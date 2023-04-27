package com.example.etudaintinscriptionfiliereservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filiere {
    @Id
    private String IdFiliere;
    @NotBlank(message = "name should not be empty")
    private String name;
    private Niveau niveau;
    @OneToMany(mappedBy = "filiere")
    private List<Inscription> inscriptions;
}
