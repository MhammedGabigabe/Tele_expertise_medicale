package org.example.teleexpertisemedicale.dao;

import jakarta.persistence.EntityManager;
import org.example.teleexpertisemedicale.entity.Patient;

import java.util.List;
import java.util.Optional;

public class PatientDAO {
    private final EntityManager entityManager;

    public PatientDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Patient patient) {
        entityManager.persist(patient);
    }

    public Optional<Patient> findById(Long id) {
        Patient patient = entityManager.find(Patient.class, id);

        return Optional.ofNullable(patient);
    }

    public List<Patient> findAll() {
        return entityManager
                .createQuery("SELECT p FROM Patient p", Patient.class)
                .getResultList();
    }

    public void update(Patient patient) {
        entityManager.merge(patient);
    }

    public void delete(Patient patient) {
        entityManager.remove(
                entityManager.contains(patient)
                        ? patient
                        : entityManager.merge(patient)
        );
    }
}
