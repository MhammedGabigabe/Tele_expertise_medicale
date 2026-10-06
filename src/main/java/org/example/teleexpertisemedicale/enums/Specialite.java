package org.example.teleexpertisemedicale.enums;

public enum Specialite {
    CARDIOLOGIE("Cardiologue"),
    PNEUMOLOGIE("Pneumologue"),
    DERMATOLOGIE("Dermatologue"),
    NEUROLOGIE("Neurologue"),
    ENDOCRINOLOGIE("Endocrinologue"),
    GASTRO_ENTEROLOGIE("Gastro-entérologue"),
    OPHTALMOLOGIE("Ophtalmologue"),
    RHUMATOLOGIE("Rhumatologue");

    private final String libelle;

    Specialite(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
