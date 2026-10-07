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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@WebFilter("/*")
public class CsrfFilter implements Filter {

    public static final String NOM_JETON = "csrfToken";

    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String jeton = (String) session.getAttribute(NOM_JETON);
        if (jeton == null) {
            jeton = genererJeton();
            session.setAttribute(NOM_JETON, jeton);
        }

        if (estRequeteModifiante(req.getMethod())) {
            String jetonRecu = req.getParameter(NOM_JETON);
            if (jetonRecu == null) {
                jetonRecu = req.getHeader("X-CSRF-Token");
            }
            if (!jetonsIdentiques(jeton, jetonRecu)) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Jeton CSRF invalide ou manquant");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    public static String genererJeton() {
        byte[] octets = new byte[32];
        RANDOM.nextBytes(octets);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(octets);
    }

    private boolean estRequeteModifiante(String methode) {
        return methode.equals("POST") || methode.equals("PUT")
                || methode.equals("DELETE") || methode.equals("PATCH");
    }

    private boolean jetonsIdentiques(String attendu, String recu) {
        if (recu == null) {
            return false;
        }
        return MessageDigest.isEqual(
                attendu.getBytes(StandardCharsets.UTF_8),
                recu.getBytes(StandardCharsets.UTF_8));
    }
}