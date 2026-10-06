package org.example.teleexpertisemedicale.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "signes_vitaux")
public class SigneVitaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String tensionArterielle;
    private Integer frequenceCardiaque;
    private Double temperature;
    private Integer frequenceRespiratoire;
    private Double poids;
    private Double taille;

    private LocalDateTime dateMesure = LocalDateTime.now();

    public SigneVitaux() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTensionArterielle() {
        return tensionArterielle;
    }
    public void setTensionArterielle(String tensionArterielle) {
        this.tensionArterielle = tensionArterielle;
    }

    public Integer getFrequenceCardiaque() {
        return frequenceCardiaque;
    }
    public void setFrequenceCardiaque(Integer frequenceCardiaque) {
        this.frequenceCardiaque = frequenceCardiaque;
    }

    public Double getTemperature() {
        return temperature;
    }
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getFrequenceRespiratoire() {
        return frequenceRespiratoire;
    }
    public void setFrequenceRespiratoire(Integer frequenceRespiratoire) {
        this.frequenceRespiratoire = frequenceRespiratoire;
    }

    public Double getPoids() {
        return poids;
    }
    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getTaille() {
        return taille;
    }
    public void setTaille(Double taille) {
        this.taille = taille;
    }

    public LocalDateTime getDateMesure() {
        return dateMesure;
    }
    public void setDateMesure(LocalDateTime dateMesure) {
        this.dateMesure = dateMesure;
    }
}