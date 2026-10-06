package org.example.teleexpertisemedicale.enums;

public enum TypeActe {
    RADIOGRAPHIE("Radiographie"),
    ECHOGRAPHIE("Échographie"),
    IRM("IRM"),
    ELECTROCARDIOGRAMME("Électrocardiogramme"),
    ACTE_DERMATOLOGIQUE_LASER("Acte dermatologique (laser)"),
    FOND_OEIL("Fond d'œil"),
    ANALYSE_SANG("Analyse de sang"),
    ANALYSE_URINE("Analyse d'urine");

    private final String libelle;

    TypeActe(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
