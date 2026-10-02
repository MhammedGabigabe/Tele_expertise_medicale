package org.example.teleexpertisemedicale.service;

import jakarta.persistence.EntityManager;
import org.example.teleexpertisemedicale.dao.PatientDAO;
import org.example.teleexpertisemedicale.entity.Patient;

import java.util.List;
import java.util.Optional;

public class PatientService {

    private final PatientDAO patientDAO;
    private final EntityManager entityManager;

    public PatientService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.patientDAO = new PatientDAO(entityManager);
    }

    public void enregistrer(Patient patient) {
        entityManager.getTransaction().begin();

        try {
            patientDAO.save(patient);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Optional<Patient> rechercherParId(Long id) {
        return patientDAO.findById(id);
    }

    public List<Patient> rechercherTous() {
        return patientDAO.findAll();
    }

    public void modifier(Patient patient) {
        entityManager.getTransaction().begin();

        try {
            patientDAO.update(patient);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void supprimer(Patient patient) {
        entityManager.getTransaction().begin();

        try {
            patientDAO.delete(patient);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
