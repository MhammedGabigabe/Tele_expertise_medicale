package org.example.teleexpertisemedicale.controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.teleexpertisemedicale.util.JPAUtil;

import java.io.IOException;

@WebServlet("/ping")
public class PingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException{

        resp.setContentType("text/plain;charset=UTF-8");
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Object result = em.createNativeQuery("SELECT 1").getSingleResult();
            resp.getWriter().println("OK- Connexion PostgreSQL réussie :"+result);
        }catch (Exception e){
            resp.setStatus(500);
            resp.getWriter().println("ERREUR :"+e.getMessage());
        }finally {
            em.close();
        }
    }
}
