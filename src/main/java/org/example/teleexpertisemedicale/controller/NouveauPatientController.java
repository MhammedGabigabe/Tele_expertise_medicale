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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/infirmier/patients/nouveau")
public class NouveauPatientController extends HttpServlet {

    private final PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/infirmier/nouveau-patient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Patient patient = new Patient();
            patient.setNom(texte(req.getParameter("nom")));
            patient.setPrenom(texte(req.getParameter("prenom")));
            patient.setNumeroSecuriteSociale(texte(req.getParameter("numeroSecuriteSociale")));
            patient.setTelephone(texte(req.getParameter("telephone")));
            patient.setAdresse(texte(req.getParameter("adresse")));
            patient.setMutuelle(texte(req.getParameter("mutuelle")));
            patient.setAntecedents(texte(req.getParameter("antecedents")));
            patient.setAllergies(texte(req.getParameter("allergies")));
            patient.setTraitementsEnCours(texte(req.getParameter("traitementsEnCours")));

            String date = texte(req.getParameter("dateNaissance"));
            if (date != null) {
                patient.setDateNaissance(LocalDate.parse(date));
            }

            SigneVitaux signes = patientService.construireSigneVitaux(
                    req.getParameter("tensionArterielle"),
                    req.getParameter("frequenceCardiaque"),
                    req.getParameter("temperature"),
                    req.getParameter("frequenceRespiratoire"),
                    req.getParameter("poids"),
                    req.getParameter("taille"));

            patientService.accueillirNouveauPatient(patient, signes);

            req.getSession().setAttribute("message", "Patient " + patient.getPrenom() + " "
                    + patient.getNom() + " enregistré et ajouté à la file d'attente");
            resp.sendRedirect(req.getContextPath() + "/infirmier/accueil");
            return;

        } catch (DateTimeParseException e) {
            req.setAttribute("erreur", "Date de naissance invalide");
        } catch (IllegalArgumentException e) {
            req.setAttribute("erreur", e.getMessage());
        }

        req.getRequestDispatcher("/WEB-INF/views/infirmier/nouveau-patient.jsp").forward(req, resp);
    }

    private String texte(String valeur) {
        return (valeur == null || valeur.isBlank()) ? null : valeur.trim();
    }
}
