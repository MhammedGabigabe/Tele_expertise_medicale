package org.example.teleexpertisemedicale.dao;

import jakarta.persistence.EntityManager;
import org.example.teleexpertisemedicale.entity.SigneVitaux;
import org.example.teleexpertisemedicale.util.JPAUtil;

import java.util.Optional;

public class SigneVitauxDao {

    public Optional<SigneVitaux> findDernierParPatient(Long patientId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT s FROM SigneVitaux s WHERE s.patient.id = :id ORDER BY s.dateMesure DESC",
                            SigneVitaux.class)
                    .setParameter("id", patientId)
                    .setMaxResults(1)
                    .getResultList()
                    .stream()
                    .findFirst();
        } finally {
            em.close();
        }
    }
}
