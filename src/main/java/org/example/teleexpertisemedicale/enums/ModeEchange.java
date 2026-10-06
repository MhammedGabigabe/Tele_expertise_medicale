package org.example.teleexpertisemedicale.enums;

public enum ModeEchange {
    SYNCHRONE("Télé-expertise synchrone"),
    ASYNCHRONE("Télé-expertise asynchrone");

    private final String libelle;

    ModeEchange(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
