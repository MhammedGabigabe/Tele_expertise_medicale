package org.example.teleexpertisemedicale.service;

import org.example.teleexpertisemedicale.dao.FileAttenteDao;
import org.example.teleexpertisemedicale.entity.FileAttente;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FileAttenteService {

    private final FileAttenteDao fileAttenteDao = new FileAttenteDao();

    public List<FileAttente> patientsDuJour() {
        return filtrerPatientsDuJour(fileAttenteDao.findAll(), LocalDate.now());
    }

    public static List<FileAttente> filtrerPatientsDuJour(List<FileAttente> entrees, LocalDate jour) {
        return entrees.stream()
                .filter(f -> f.getDateArrivee() != null
                        && f.getDateArrivee().toLocalDate().equals(jour))
                .sorted(Comparator.comparing(FileAttente::getDateArrivee))
                .toList();
    }

    public List<FileAttente> enAttente() {
        return fileAttenteDao.findEnAttente();
    }

    public Optional<FileAttente> trouverEntree(Long id) {
        return fileAttenteDao.findById(id);
    }

}
