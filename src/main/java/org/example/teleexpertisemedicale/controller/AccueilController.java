package org.example.teleexpertisemedicale.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.teleexpertisemedicale.entity.Utilisateur;

import java.io.IOException;

@WebServlet("/accueil")
public class AccueilController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Utilisateur utilisateur = (Utilisateur) req.getSession().getAttribute("utilisateur");
        String contexte = req.getContextPath();

        switch (utilisateur.getRole()) {
            case INFIRMIER -> resp.sendRedirect(contexte + "/infirmier/accueil");
            case GENERALISTE -> resp.sendRedirect(contexte + "/generaliste/accueil");
            case SPECIALISTE -> resp.sendRedirect(contexte + "/specialiste/accueil");
        }
    }
}