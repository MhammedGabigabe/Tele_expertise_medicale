package org.example.teleexpertisemedicale.util;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class RequeteUtil {
    private RequeteUtil(){}

    public static Optional<Long> lireLong(HttpServletRequest req, String nom) {
        String valeur = req.getParameter(nom);
        if (valeur == null || valeur.isBlank()) {
            return Optional.empty();
        }
        try {
            return Optional.of(Long.valueOf(valeur.trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
