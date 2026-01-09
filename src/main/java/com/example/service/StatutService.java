package com.example.service;

import com.example.domain.Statut;
import com.example.dao.StatutRepository;

import java.util.List;

public class StatutService {

    private final StatutRepository repository = new StatutRepository();

    public Statut createStatut(Statut statut) {
        repository.save(statut);
        return statut;
    }

    public List<Statut> getAllStatuts() {
        return repository.findAll();
    }

    public Statut getStatutById(Long id) {
        return repository.findById(id);
    }

    public Statut updateStatut(Long id, Statut statut) {
        return repository.update(id, statut);
    }

    public boolean deleteStatut(Long id) {
        return repository.delete(id);
    }
}
