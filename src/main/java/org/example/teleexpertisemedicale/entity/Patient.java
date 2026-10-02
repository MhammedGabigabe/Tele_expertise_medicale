package org.example.teleexpertisemedicale.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Patient {

    @Id
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    private String numeroSecuriteSociale;
    private String telephone;
    private String adresse;
    private Double tensionArterielle;
    private Integer frequenceCardiaque;
    private Double temperature;
    private Integer frequenceRespiratoire;
    private Double poids;
    private Double taille;

    public Patient() {
    }

}
