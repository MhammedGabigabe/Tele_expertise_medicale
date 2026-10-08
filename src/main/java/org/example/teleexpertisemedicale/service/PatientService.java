package org.example.teleexpertisemedicale.service;

import jakarta.persistence.EntityManager;
import org.example.teleexpertisemedicale.dao.PatientDao;
import org.example.teleexpertisemedicale.entity.FileAttente;
import org.example.teleexpertisemedicale.entity.Patient;
import org.example.teleexpertisemedicale.entity.SigneVitaux;
import org.example.teleexpertisemedicale.util.JPAUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientService {

    private final PatientDao patientDao = new PatientDao();

    public List<Patient> rechercher(String texte) {
        if (texte == null || texte.isBlank()) {
            return new ArrayList<>();
        }
        return patientDao.rechercher(texte.trim());
    }

    public Optional<Patient> trouverParId(Long id) {
        return patientDao.findById(id);
    }

    public void accueillirNouveauPatient(Patient patient, SigneVitaux signes) {
        validerPatient(patient);

        if (patientDao.findByNumeroSecuriteSociale(patient.getNumeroSecuriteSociale()).isPresent()) {
            throw new IllegalArgumentException("Un patient avec ce numéro de sécurité sociale existe déjà");
        }

        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(patient);
            enregistrerPassage(em, patient, signes);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void accueillirPatientExistant(Long patientId, SigneVitaux signes) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Patient patient = em.find(Patient.class, patientId);
            if (patient == null) {
                throw new IllegalArgumentException("Patient introuvable");
            }

            Long dejaEnAttente = em.createQuery(
                            "SELECT COUNT(f) FROM FileAttente f "
                                    + "WHERE f.patient.id = :id AND f.consulte = false AND f.dateArrivee >= :debut",
                            Long.class)
                    .setParameter("id", patientId)
                    .setParameter("debut", LocalDate.now().atStartOfDay())
                    .getSingleResult();
            if (dejaEnAttente > 0) {
                throw new IllegalArgumentException("Ce patient est déjà dans la file d'attente");
            }

            enregistrerPassage(em, patient, signes);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    private void enregistrerPassage(EntityManager em, Patient patient, SigneVitaux signes) {
        signes.setPatient(patient);
        em.persist(signes);

        FileAttente fileAttente = new FileAttente();
        fileAttente.setPatient(patient);
        fileAttente.setSignesVitaux(signes);
        em.persist(fileAttente);
    }

    public SigneVitaux construireSigneVitaux(String tension, String frequenceCardiaque,
                                             String temperature, String frequenceRespiratoire,
                                             String poids, String taille) {
        String tensionNettoyee = tension == null ? "" : tension.trim();
        if (!tensionNettoyee.matches("\\d{2,3}/\\d{2,3}")) {
            throw new IllegalArgumentException("Tension artérielle invalide (format attendu : 120/80)");
        }

        SigneVitaux signes = new SigneVitaux();
        signes.setTensionArterielle(tensionNettoyee);
        signes.setFrequenceCardiaque(lireEntier(frequenceCardiaque, "Fréquence cardiaque", 20, 250));
        signes.setTemperature(lireDecimal(temperature, "Température", 30, 45));
        signes.setFrequenceRespiratoire(lireEntier(frequenceRespiratoire, "Fréquence respiratoire", 5, 60));
        signes.setPoids(lireDecimalOptionnel(poids, "Poids", 1, 500));
        signes.setTaille(lireDecimalOptionnel(taille, "Taille", 30, 250));
        return signes;
    }

    private void validerPatient(Patient patient) {
        if (patient.getNom() == null) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (patient.getPrenom() == null) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }
        if (patient.getDateNaissance() == null) {
            throw new IllegalArgumentException("La date de naissance est obligatoire");
        }
        if (patient.getDateNaissance().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La date de naissance ne peut pas être dans le futur");
        }
        if (patient.getNumeroSecuriteSociale() == null) {
            throw new IllegalArgumentException("Le numéro de sécurité sociale est obligatoire");
        }
    }

    private Integer lireEntier(String valeur, String nom, int min, int max) {
        if (valeur == null || valeur.isBlank()) {
            throw new IllegalArgumentException(nom + " obligatoire");
        }
        int nombre;
        try {
            nombre = Integer.parseInt(valeur.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(nom + " doit être un nombre entier");
        }
        if (nombre < min || nombre > max) {
            throw new IllegalArgumentException(nom + " doit être comprise entre " + min + " et " + max);
        }
        return nombre;
    }

    private Double lireDecimal(String valeur, String nom, double min, double max) {
        if (valeur == null || valeur.isBlank()) {
            throw new IllegalArgumentException(nom + " obligatoire");
        }
        double nombre;
        try {
            nombre = Double.parseDouble(valeur.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(nom + " doit être un nombre");
        }
        if (nombre < min || nombre > max) {
            throw new IllegalArgumentException(nom + " doit être comprise entre " + min + " et " + max);
        }
        return nombre;
    }

    private Double lireDecimalOptionnel(String valeur, String nom, double min, double max) {
        if (valeur == null || valeur.isBlank()) {
            return null;
        }
        return lireDecimal(valeur, nom, min, max);
    }

}
