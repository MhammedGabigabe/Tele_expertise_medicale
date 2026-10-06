package org.example.teleexpertisemedicale.dao;

import jakarta.persistence.EntityManager;
import org.example.teleexpertisemedicale.entity.Utilisateur;
import org.example.teleexpertisemedicale.enums.Role;
import org.example.teleexpertisemedicale.util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class UtilisateurDao {

    public Utilisateur save(Utilisateur utilisateur) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (utilisateur.getId() == null) {
                em.persist(utilisateur);
            } else {
                utilisateur = em.merge(utilisateur);
            }
            em.getTransaction().commit();
            return utilisateur;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Optional<Utilisateur> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            Utilisateur utilisateur = em.find(Utilisateur.class, id);
            return Optional.ofNullable(utilisateur);
        } finally {
            em.close();
        }
    }

    public Optional<Utilisateur> findByEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class)
                    .setParameter("email", email)
                    .getResultStream()
                    .findFirst();

        } finally {
            em.close();
        }
    }

    public List<Utilisateur> findByRole(Role role) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM Utilisateur u WHERE u.role = :role", Utilisateur.class)
                    .setParameter("role", role)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public long count() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT COUNT(u) FROM Utilisateur u", Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
