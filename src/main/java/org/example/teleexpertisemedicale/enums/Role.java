package org.example.teleexpertisemedicale.enums;

public enum Role {
    INFIRMIER("Infirmier"),
    GENERALISTE("Médecin généraliste"),
    SPECIALISTE("Médecin spécialiste");

    private final String libelle;

    Role(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
