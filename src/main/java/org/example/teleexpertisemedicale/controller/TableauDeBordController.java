package org.example.teleexpertisemedicale.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/infirmier/accueil", "/generaliste/accueil", "/specialiste/accueil"})
public class TableauDeBordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String vue = "/WEB-INF/views" + req.getServletPath() + ".jsp";
        req.getRequestDispatcher(vue).forward(req, resp);
    }
}