package org.example.teleexpertisemedicale.entity;

import jakarta.persistence.*;
import org.example.teleexpertisemedicale.enums.TypeActe;

@Entity
@Table(name = "actes_techniques")
public class ActeTechnique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    @Enumerated(EnumType.STRING)
    private TypeActe typeActe;

    private Double cout;

    private ActeTechnique(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public TypeActe getTypeActe() {
        return typeActe;
    }

    public void setTypeActe(TypeActe typeActe) {
        this.typeActe = typeActe;
    }

    public Double getCout() {
        return cout;
    }

    public void setCout(Double cout) {
        this.cout = cout;
    }
}
