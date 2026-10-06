package org.example.teleexpertisemedicale.enums;

public enum StatutDemande {
    EN_ATTENTE("En attente"),
    TERMINEE("Terminée");

    private final String libelle;

    StatutDemande(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
