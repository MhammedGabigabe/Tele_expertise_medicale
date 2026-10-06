package org.example.teleexpertisemedicale.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "file_attente")
public class FileAttente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "signes_vitaux_id")
    private SigneVitaux signesVitaux;

    private LocalDate dateArrivee = LocalDate.now();

    private boolean consulte = false;

    public FileAttente(){}

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

    public SigneVitaux getSignesVitaux() {
        return signesVitaux;
    }

    public void setSignesVitaux(SigneVitaux signesVitaux) {
        this.signesVitaux = signesVitaux;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public boolean isConsulte() {
        return consulte;
    }

    public void setConsulte(boolean consulte) {
        this.consulte = consulte;
    }
}
