package org.example.teleexpertisemedicale.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.teleexpertisemedicale.entity.Consultation;
import org.example.teleexpertisemedicale.entity.Utilisateur;
import org.example.teleexpertisemedicale.service.ConsultationService;
import org.example.teleexpertisemedicale.util.RequeteUtil;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/generaliste/consultations/detail")
public class ConsultationController extends HttpServlet {

    private final ConsultationService consultationService = new ConsultationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Optional<Consultation> consultation = chargerConsultation(req);

        if (consultation.isEmpty()) {
            req.getSession().setAttribute("messageErreur", "Consultation introuvable");
            resp.sendRedirect(req.getContextPath() + "/generaliste/consultations");
            return;
        }

        afficher(req, resp, consultation.get());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = RequeteUtil.lireLong(req, "id").orElse(null);
        Utilisateur generaliste = (Utilisateur) req.getSession().getAttribute("utilisateur");

        if (id == null) {
            resp.sendRedirect(req.getContextPath() + "/generaliste/consultations");
            return;
        }

        try {
            consultationService.cloturer(id, generaliste.getId(),
                    req.getParameter("diagnostic"), req.getParameter("traitement"));

            req.getSession().setAttribute("message", "Consultation clôturée");
            resp.sendRedirect(req.getContextPath() + "/generaliste/consultations");
            return;

        } catch (IllegalArgumentException e) {
            Optional<Consultation> consultation = chargerConsultation(req);

            if (consultation.isEmpty()) {
                req.getSession().setAttribute("messageErreur", e.getMessage());
                resp.sendRedirect(req.getContextPath() + "/generaliste/consultations");
                return;
            }

            req.setAttribute("erreur", e.getMessage());
            afficher(req, resp, consultation.get());
        }
    }

    private Optional<Consultation> chargerConsultation(HttpServletRequest req) {
        Utilisateur generaliste = (Utilisateur) req.getSession().getAttribute("utilisateur");
        return RequeteUtil.lireLong(req, "id")
                .flatMap(id -> consultationService.trouverPourGeneraliste(id, generaliste.getId()));
    }

    private void afficher(HttpServletRequest req, HttpServletResponse resp, Consultation consultation)
            throws ServletException, IOException {
        req.setAttribute("consultation", consultation);
        req.setAttribute("patient", consultation.getPatient());
        req.setAttribute("signes", consultationService
                .derniersSignesVitaux(consultation.getPatient().getId()).orElse(null));
        req.getRequestDispatcher("/WEB-INF/views/generaliste/consultation.jsp").forward(req, resp);
    }
}