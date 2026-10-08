package org.example.teleexpertisemedicale.dao;

import jakarta.persistence.EntityManager;
import org.example.teleexpertisemedicale.entity.Patient;
import org.example.teleexpertisemedicale.util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class PatientDao {

    public Patient save(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (patient.getId() == null) {
                em.persist(patient);
            } else {
                patient = em.merge(patient);
            }
            em.getTransaction().commit();
            return patient;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Optional<Patient> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Patient.class, id));
        } finally {
            em.close();
        }
    }

    public Optional<Patient> findByNumeroSecuriteSociale(String numero) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT p FROM Patient p WHERE p.numeroSecuriteSociale = :numero", Patient.class)
                    .setParameter("numero", numero)
                    .getResultList()
                    .stream()
                    .findFirst();
        } finally {
            em.close();
        }
    }

    public List<Patient> rechercher(String texte) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT p FROM Patient p WHERE p.numeroSecuriteSociale = :texte "
                                    + "OR LOWER(p.nom) LIKE :motif OR LOWER(p.prenom) LIKE :motif",
                            Patient.class)
                    .setParameter("texte", texte)
                    .setParameter("motif", "%" + texte.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Patient> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Patient p", Patient.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
