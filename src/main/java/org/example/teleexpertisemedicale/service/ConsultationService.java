package org.example.teleexpertisemedicale.service;

import jakarta.persistence.EntityManager;
import org.example.teleexpertisemedicale.dao.ConsultationDao;
import org.example.teleexpertisemedicale.dao.SigneVitauxDao;
import org.example.teleexpertisemedicale.entity.Consultation;
import org.example.teleexpertisemedicale.entity.FileAttente;
import org.example.teleexpertisemedicale.entity.SigneVitaux;
import org.example.teleexpertisemedicale.entity.Utilisateur;
import org.example.teleexpertisemedicale.enums.StatutConsultation;
import org.example.teleexpertisemedicale.util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class ConsultationService {

    private final ConsultationDao consultationDao = new ConsultationDao();
    private final SigneVitauxDao signeVitauxDao = new SigneVitauxDao();

    public Consultation demarrer(Long fileAttenteId, Utilisateur generaliste,
                                 String motif, String observations) {
        if (motif == null || motif.isBlank()) {
            throw new IllegalArgumentException("Le motif de la consultation est obligatoire");
        }

        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            int modifies = em.createQuery(
                            "UPDATE FileAttente f SET f.consulte = true "
                                    + "WHERE f.id = :id AND f.consulte = false")
                    .setParameter("id", fileAttenteId)
                    .executeUpdate();
            if (modifies == 0) {
                throw new IllegalArgumentException(
                        "Ce patient a déjà été pris en charge ou n'est plus dans la file d'attente");
            }

            FileAttente entree = em.find(FileAttente.class, fileAttenteId);

            Consultation consultation = new Consultation();
            consultation.setPatient(entree.getPatient());
            consultation.setGeneraliste(em.find(Utilisateur.class, generaliste.getId()));
            consultation.setMotif(motif.trim());
            consultation.setObservations(nettoyer(observations));
            em.persist(consultation);

            em.getTransaction().commit();
            return consultation;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Consultation cloturer(Long consultationId, Long generalisteId,
                                 String diagnostic, String traitement) {
        if (diagnostic == null || diagnostic.isBlank()) {
            throw new IllegalArgumentException("Le diagnostic est obligatoire");
        }
        if (traitement == null || traitement.isBlank()) {
            throw new IllegalArgumentException("Le traitement prescrit est obligatoire");
        }

        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Consultation consultation = em.find(Consultation.class, consultationId);

            if (consultation == null || !consultation.getGeneraliste().getId().equals(generalisteId)) {
                throw new IllegalArgumentException("Consultation introuvable");
            }
            if (consultation.getStatut() != StatutConsultation.EN_COURS) {
                throw new IllegalArgumentException("Cette consultation ne peut plus être clôturée");
            }

            consultation.setDiagnostic(diagnostic.trim());
            consultation.setTraitement(traitement.trim());
            consultation.setStatut(StatutConsultation.TERMINEE);

            em.getTransaction().commit();
            return consultation;
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Optional<Consultation> trouverPourGeneraliste(Long consultationId, Long generalisteId) {
        return consultationDao.findById(consultationId)
                .filter(c -> c.getGeneraliste().getId().equals(generalisteId));
    }

    public List<Consultation> consultationsDuGeneraliste(Long generalisteId) {
        return consultationDao.findByGeneraliste(generalisteId);
    }

    public Optional<SigneVitaux> derniersSignesVitaux(Long patientId) {
        return signeVitauxDao.findDernierParPatient(patientId);
    }

    private String nettoyer(String valeur) {
        return (valeur == null || valeur.isBlank()) ? null : valeur.trim();
    }

}
