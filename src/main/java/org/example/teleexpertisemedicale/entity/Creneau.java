package org.example.teleexpertisemedicale.entity;

import jakarta.persistence.*;
import org.example.teleexpertisemedicale.enums.StatutCreneau;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "creneaux")
public class Creneau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "specialiste_id")
    private Utilisateur specialiste;

    private LocalDate dateCreneau;
    private LocalTime heureDebut;
    private LocalTime heureFin;

    @Enumerated(EnumType.STRING)
    private StatutCreneau statut = StatutCreneau.DISPONIBLE;

    public Creneau(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getSpecialiste() {
        return specialiste;
    }

    public void setSpecialiste(Utilisateur specialiste) {
        this.specialiste = specialiste;
    }

    public LocalDate getDateCreneau() {
        return dateCreneau;
    }

    public void setDateCreneau(LocalDate dateCreneau) {
        this.dateCreneau = dateCreneau;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public StatutCreneau getStatut() {
        return statut;
    }

    public void setStatut(StatutCreneau statut) {
        this.statut = statut;
    }
}
