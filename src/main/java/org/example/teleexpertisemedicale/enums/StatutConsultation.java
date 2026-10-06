package org.example.teleexpertisemedicale.enums;

public enum StatutConsultation {
    EN_COURS("En cours"),
    EN_ATTENTE_AVIS_SPECIALISTE("En attente d'avis spécialiste"),
    TERMINEE("Terminée");

    private final String libelle;

    StatutConsultation(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
