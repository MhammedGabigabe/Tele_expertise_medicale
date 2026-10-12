package org.example.teleexpertisemedicale.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.teleexpertisemedicale.entity.Utilisateur;
import org.example.teleexpertisemedicale.service.ConsultationService;

import java.io.IOException;

@WebServlet("/generaliste/consultations")
public class ListeConsultationsController extends HttpServlet {

    private final ConsultationService consultationService = new ConsultationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Utilisateur generaliste = (Utilisateur) req.getSession().getAttribute("utilisateur");
        req.setAttribute("consultations",
                consultationService.consultationsDuGeneraliste(generaliste.getId()));
        req.getRequestDispatcher("/WEB-INF/views/generaliste/consultations.jsp").forward(req, resp);
    }
}