package org.example.teleexpertisemedicale.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.teleexpertisemedicale.dao.UtilisateurDao;
import org.example.teleexpertisemedicale.entity.Utilisateur;

import java.util.Optional;

public class AuthService {

    private final UtilisateurDao utilisateurDao = new UtilisateurDao();

    public Utilisateur creerUtilisateur(Utilisateur utilisateur, String motDePasseClair) {
        if (utilisateurDao.findByEmail(utilisateur.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Cet email est déjà utilisé");
        }
        utilisateur.setMotDePasse(hasher(motDePasseClair));
        return utilisateurDao.save(utilisateur);
    }

    public Utilisateur authentifier(String email, String motDePasse) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findByEmail(email);
        if (optionalUtilisateur.isEmpty()) {
            return null;
        }

        Utilisateur utilisateur = optionalUtilisateur.get();

        boolean correct = BCrypt.verifyer()
                .verify(motDePasse.toCharArray(), utilisateur.getMotDePasse())
                .verified;
        return correct ? utilisateur : null;
    }

    private String hasher(String motDePasseClair) {
        return BCrypt.withDefaults().hashToString(12, motDePasseClair.toCharArray());
    }
}
