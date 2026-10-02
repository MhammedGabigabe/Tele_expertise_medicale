package org.example.teleexpertisemedicale;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.teleexpertisemedicale.entity.Patient;
import org.example.teleexpertisemedicale.service.PatientService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("teleExpertisePU");

        EntityManager entityManager =
                entityManagerFactory.createEntityManager();
        try {


            PatientService patientService =
                    new PatientService(entityManager);

            Patient patient = new Patient();

            patient.setNom("alaoui");
            patient.setPrenom("ahmed");
            patient.setDateNaissance(LocalDate.of(1995, 5, 15));
            patient.setNumeroSecuriteSociale("SS123456789");
            patient.setTelephone("0612345678");
            patient.setAdresse("Casablanca");
            patient.setTensionArterielle(12.5);
            patient.setFrequenceCardiaque(75);
            patient.setTemperature(36.7);
            patient.setFrequenceRespiratoire(16);
            patient.setPoids(70.5);
            patient.setTaille(175.0);

            patientService.enregistrer(patient);

            System.out.println("Patient enregistré avec succès.");
            System.out.println("ID généré : " + patient.getId());
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
