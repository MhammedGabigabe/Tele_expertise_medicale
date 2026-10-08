package org.example.teleexpertisemedicale.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.teleexpertisemedicale.service.FileAttenteService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/infirmier/patients/liste")
public class ListePatientsController extends HttpServlet {

    private final FileAttenteService fileAttenteService = new FileAttenteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("entrees", fileAttenteService.patientsDuJour());
        req.setAttribute("jour", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        req.getRequestDispatcher("/WEB-INF/views/infirmier/liste.jsp").forward(req, resp);
    }
}
