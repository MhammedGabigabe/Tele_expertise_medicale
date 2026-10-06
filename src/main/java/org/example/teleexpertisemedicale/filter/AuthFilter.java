package org.example.teleexpertisemedicale.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.teleexpertisemedicale.entity.Utilisateur;
import org.example.teleexpertisemedicale.enums.Role;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String chemin = req.getRequestURI().substring(req.getContextPath().length());

        if (estPublic(chemin)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        Utilisateur utilisateur = null;
        if (session != null) {
            utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        }

        if (utilisateur == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (!aAcces(chemin, utilisateur.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès refusé");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean estPublic(String chemin) {
        return chemin.equals("/")
                || chemin.equals("/index.jsp")
                || chemin.equals("/login")
                || chemin.equals("/ping")
                || chemin.startsWith("/css/")
                || chemin.startsWith("/js/");
    }

    private boolean aAcces(String chemin, Role role) {
        if (chemin.startsWith("/infirmier/")) {
            return role == Role.INFIRMIER;
        }
        if (chemin.startsWith("/generaliste/")) {
            return role == Role.GENERALISTE;
        }
        if (chemin.startsWith("/specialiste/")) {
            return role == Role.SPECIALISTE;
        }
        return true;
    }
}