package tn.esprit.logicielservice.services;

import tn.esprit.logicielservice.entities.ListeLogiciel;

import java.util.List;
import java.util.Optional;

public interface IListeLogiciel {
    ListeLogiciel createLogiciel(ListeLogiciel logiciel);

    List<ListeLogiciel> getAllLogiciels();

    Optional<ListeLogiciel> getLogicielById(Long idLogiciel);

    ListeLogiciel updateLogiciel(Long idLogiciel, ListeLogiciel logiciel);

    void deleteLogiciel(Long idLogiciel);
    List<ListeLogiciel> searchByNomLogiciel(String nom);
    List<ListeLogiciel> searchByVersion(String version);

}
