package org.example.teleexpertisemedicale.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.teleexpertisemedicale.entity.Patient;
import org.example.teleexpertisemedicale.entity.SigneVitaux;
import org.example.teleexpertisemedicale.service.PatientService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/infirmier/patients/admission")
public class PatientExistantController extends HttpServlet {

    private final PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Optional<Patient> patient = chargerPatient(req);

        if (patient.isEmpty()) {
            resp.sendRedirect(req.getContextPath()
                    + "/infirmier/patients/recherche");
            return;
        }

        req.setAttribute("patient", patient.get());

        req.getRequestDispatcher(
                "/WEB-INF/views/infirmier/admission.jsp"
        ).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Optional<Patient> patient = chargerPatient(req);

        if (patient.isEmpty()) {
            resp.sendRedirect(req.getContextPath()
                    + "/infirmier/patients/recherche");
            return;
        }

        try {
            SigneVitaux signes = patientService.construireSigneVitaux(
                    req.getParameter("tensionArterielle"),
                    req.getParameter("frequenceCardiaque"),
                    req.getParameter("temperature"),
                    req.getParameter("frequenceRespiratoire"),
                    req.getParameter("poids"),
                    req.getParameter("taille"));

            patientService.accueillirPatientExistant(patient.get().getId(), signes);

            req.getSession().setAttribute("message", "Signes vitaux enregistrés : "
                    + patient.get().getPrenom() + " " + patient.get().getNom() + " est dans la file d'attente");
            resp.sendRedirect(req.getContextPath() + "/infirmier/accueil");
            return;

        } catch (IllegalArgumentException e) {
            req.setAttribute("erreur", e.getMessage());
        }

        req.setAttribute("patient", patient.get());
        req.getRequestDispatcher("/WEB-INF/views/infirmier/admission.jsp").forward(req, resp);
    }

    private Optional<Patient> chargerPatient(HttpServletRequest req) {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            return patientService.trouverParId(id);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
