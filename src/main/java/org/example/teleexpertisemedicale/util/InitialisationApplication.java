package org.example.teleexpertisemedicale.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.teleexpertisemedicale.dao.UtilisateurDao;
import org.example.teleexpertisemedicale.entity.Utilisateur;
import org.example.teleexpertisemedicale.enums.Role;
import org.example.teleexpertisemedicale.enums.Specialite;
import org.example.teleexpertisemedicale.service.AuthService;

@WebListener
public class InitialisationApplication implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        AuthService authService = new AuthService();

        if (utilisateurDao.count() == 0) {
            Utilisateur infirmier = new Utilisateur();
            infirmier.setNom("Alaoui");
            infirmier.setPrenom("Sara");
            infirmier.setEmail("infirmier@test.com");
            infirmier.setRole(Role.INFIRMIER);
            authService.creerUtilisateur(infirmier, "password123");

            Utilisateur generaliste = new Utilisateur();
            generaliste.setNom("Benali");
            generaliste.setPrenom("Youssef");
            generaliste.setEmail("generaliste@test.com");
            generaliste.setRole(Role.GENERALISTE);
            authService.creerUtilisateur(generaliste, "password123");

            Utilisateur specialiste = new Utilisateur();
            specialiste.setNom("Idrissi");
            specialiste.setPrenom("Karim");
            specialiste.setEmail("specialiste@test.com");
            specialiste.setRole(Role.SPECIALISTE);
            specialiste.setSpecialite(Specialite.CARDIOLOGIE);
            specialiste.setTarif(300.0);
            authService.creerUtilisateur(specialiste, "password123");

            System.out.println("[INIT] Comptes de test créés");
        }

        Utilisateur connecte = authService.authentifier("generaliste@test.com", "password123");
        System.out.println("[INIT] Connexion réussie : " + (connecte != null ? connecte.getNom() : "ÉCHEC"));

        Utilisateur refuse = authService.authentifier("generaliste@test.com", "mauvais");
        System.out.println("[INIT] Mauvais mot de passe refusé : " + (refuse == null));
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        JPAUtil.close();
    }
}