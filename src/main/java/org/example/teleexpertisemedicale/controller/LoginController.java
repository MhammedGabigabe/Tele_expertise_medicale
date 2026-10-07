package org.example.teleexpertisemedicale.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.teleexpertisemedicale.entity.Utilisateur;
import org.example.teleexpertisemedicale.filter.CsrfFilter;
import org.example.teleexpertisemedicale.service.AuthService;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("utilisateur") != null) {
            resp.sendRedirect(req.getContextPath() + "/accueil");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String motDePasse = req.getParameter("motDePasse");

        if (email == null || email.isBlank() || motDePasse == null || motDePasse.isBlank()) {
            req.setAttribute("erreur", "Veuillez remplir tous les champs");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        Utilisateur utilisateur = authService.authentifier(email.trim(), motDePasse);

        if (utilisateur == null) {
            req.setAttribute("erreur", "Email ou mot de passe incorrect");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        req.changeSessionId();
        session.setAttribute("utilisateur", utilisateur);
        session.setAttribute(CsrfFilter.NOM_JETON, CsrfFilter.genererJeton());
        session.setMaxInactiveInterval(30 * 60);

        resp.sendRedirect(req.getContextPath() + "/accueil");
    }
}