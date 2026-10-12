package org.example.teleexpertisemedicale.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.teleexpertisemedicale.service.FileAttenteService;

import java.io.IOException;

@WebServlet("/generaliste/file-attente")
public class FileAttenteController extends HttpServlet {

    private final FileAttenteService fileAttenteService = new FileAttenteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("entrees", fileAttenteService.enAttente());
        req.getRequestDispatcher("/WEB-INF/views/generaliste/file-attente.jsp").forward(req, resp);
    }


}
