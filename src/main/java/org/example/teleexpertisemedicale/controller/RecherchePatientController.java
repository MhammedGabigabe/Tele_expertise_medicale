package org.example.teleexpertisemedicale.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.teleexpertisemedicale.service.PatientService;

import java.io.IOException;

@WebServlet("/infirmier/patients/recherche")
public class RecherchePatientController extends HttpServlet {

    private final PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String q = req.getParameter("q");

        if (q != null && !q.isBlank()) {
            req.setAttribute("q", q.trim());
            req.setAttribute("patients", patientService.rechercher(q));
        }

        req.getRequestDispatcher("/WEB-INF/views/infirmier/recherche.jsp").forward(req, resp);
    }
}
