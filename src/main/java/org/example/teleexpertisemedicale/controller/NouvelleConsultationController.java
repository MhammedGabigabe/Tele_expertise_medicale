package org.example.teleexpertisemedicale.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.teleexpertisemedicale.entity.Consultation;
import org.example.teleexpertisemedicale.entity.FileAttente;
import org.example.teleexpertisemedicale.entity.Utilisateur;
import org.example.teleexpertisemedicale.service.ConsultationService;
import org.example.teleexpertisemedicale.service.FileAttenteService;
import org.example.teleexpertisemedicale.util.RequeteUtil;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/generaliste/consultations/nouvelle")
public class NouvelleConsultationController extends HttpServlet {

    private final FileAttenteService fileAttenteService = new FileAttenteService();
    private final ConsultationService consultationService = new ConsultationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Optional<FileAttente> entree = RequeteUtil.lireLong(req, "fileId")
                .flatMap(fileAttenteService::trouverEntree);

        if (entree.isEmpty() || entree.get().isConsulte()) {
            req.getSession().setAttribute("messageErreur", "Ce patient n'est plus disponible dans la file d'attente");
            resp.sendRedirect(req.getContextPath() + "/generaliste/file-attente");
            return;
        }

        afficher(req, resp, entree.get());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long fileId = RequeteUtil.lireLong(req, "fileId").orElse(null);
        if (fileId == null) {
            resp.sendRedirect(req.getContextPath() + "/generaliste/file-attente");
            return;
        }

        Utilisateur generaliste = (Utilisateur) req.getSession().getAttribute("utilisateur");

        try {
            Consultation consultation = consultationService.demarrer(
                    fileId, generaliste, req.getParameter("motif"), req.getParameter("observations"));

            req.getSession().setAttribute("message", "Consultation créée");
            resp.sendRedirect(req.getContextPath()
                    + "/generaliste/consultations/detail?id=" + consultation.getId());
            return;

        } catch (IllegalArgumentException e) {
            Optional<FileAttente> entree = fileAttenteService.trouverEntree(fileId);

            if (entree.isEmpty() || entree.get().isConsulte()) {
                req.getSession().setAttribute("messageErreur", e.getMessage());
                resp.sendRedirect(req.getContextPath() + "/generaliste/file-attente");
                return;
            }

            req.setAttribute("erreur", e.getMessage());
            afficher(req, resp, entree.get());
        }
    }

    private void afficher(HttpServletRequest req, HttpServletResponse resp, FileAttente entree)
            throws ServletException, IOException {
        req.setAttribute("entree", entree);
        req.setAttribute("patient", entree.getPatient());
        req.setAttribute("signes", entree.getSignesVitaux());
        req.getRequestDispatcher("/WEB-INF/views/generaliste/nouvelle-consultation.jsp").forward(req, resp);
    }
}