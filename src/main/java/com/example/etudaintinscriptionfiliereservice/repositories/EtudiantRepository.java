package com.example.etudaintinscriptionfiliereservice.repositories;

import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Etudiant findByApogee(String apogee);

}
