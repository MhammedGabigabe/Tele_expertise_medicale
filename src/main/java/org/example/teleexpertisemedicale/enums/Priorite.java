package org.example.teleexpertisemedicale.enums;

public enum Priorite {
    URGENTE("Urgente"),
    NORMALE("Normale"),
    NON_URGENTE("Non urgente");

    private final String libelle;

    Priorite(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
