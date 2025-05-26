package tn.esprit.logicielservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.logicielservice.entities.ListeLogiciel;
import tn.esprit.logicielservice.repositories.ListeLogicielRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ListeLogicielService implements IListeLogiciel{
    @Autowired
    private ListeLogicielRepository listeLogicielRepository;

    @Override
    public ListeLogiciel createLogiciel(ListeLogiciel logiciel) {
        return listeLogicielRepository.save(logiciel);
    }

    @Override
    public List<ListeLogiciel> getAllLogiciels() {
        return listeLogicielRepository.findAll();
    }

    @Override
    public Optional<ListeLogiciel> getLogicielById(Long idLogiciel) {
        return listeLogicielRepository.findById(idLogiciel);
    }

    @Override
    public ListeLogiciel updateLogiciel(Long idLogiciel, ListeLogiciel logiciel) {
        if (listeLogicielRepository.existsById(idLogiciel)) {
            logiciel.setIdLogiciel(idLogiciel);
            return listeLogicielRepository.save(logiciel);
        }
        return null;
    }

    @Override
    public void deleteLogiciel(Long idLogiciel) {
        listeLogicielRepository.deleteById(idLogiciel);
    }

    @Override
    public List<ListeLogiciel> searchByNomLogiciel(String nom) {
        return listeLogicielRepository.findByNomLogicielContainingIgnoreCase(nom);
    }

    @Override
    public List<ListeLogiciel> searchByVersion(String version) {
        return listeLogicielRepository.findByVersionContainingIgnoreCase(version);
    }
}
