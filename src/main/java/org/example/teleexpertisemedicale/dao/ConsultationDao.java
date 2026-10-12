package org.example.teleexpertisemedicale.dao;

import jakarta.persistence.EntityManager;
import org.example.teleexpertisemedicale.entity.Consultation;
import org.example.teleexpertisemedicale.util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class ConsultationDao {

    public Optional<Consultation> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(Consultation.class, id));
        } finally {
            em.close();
        }
    }

    public List<Consultation> findByGeneraliste(Long generalisteId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT c FROM Consultation c WHERE c.generaliste.id = :id "
                                    + "ORDER BY c.dateConsultation DESC",
                            Consultation.class)
                    .setParameter("id", generalisteId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
