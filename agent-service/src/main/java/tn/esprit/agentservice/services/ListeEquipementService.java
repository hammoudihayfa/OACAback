package tn.esprit.agentservice.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.agentservice.entities.ListeEquipementsParAgent;
import tn.esprit.agentservice.repositories.ListeEquipementRepository;

import java.util.List;
import java.util.Optional;

@Service

public class ListeEquipementService implements IListeEquipement{
    private final ListeEquipementRepository listeEquipementRepository;
    @Autowired
    public ListeEquipementService(ListeEquipementRepository listeEquipementRepository) {
        this.listeEquipementRepository = listeEquipementRepository;
    }
    @Override
    public ListeEquipementsParAgent ajouterEquipementParAgent(ListeEquipementsParAgent equipement) {
        return listeEquipementRepository.save(equipement);


    } @Override
    public List<ListeEquipementsParAgent> getEquipementsParAgent() {
        return listeEquipementRepository.findAll();
    }

    @Override
    public Optional<ListeEquipementsParAgent> getEquipementParAgentById(Long idEquipementParAgent) {
        return listeEquipementRepository.findById(idEquipementParAgent);
    }

    @Override
    public ListeEquipementsParAgent updateEquipementParAgent(Long idEquipementParAgent, ListeEquipementsParAgent equipement) {
        Optional<ListeEquipementsParAgent> existingEquipement = listeEquipementRepository.findById(idEquipementParAgent);
        if (existingEquipement.isPresent()) {
            ListeEquipementsParAgent equipementToUpdate = existingEquipement.get();
            equipementToUpdate.setNature(equipement.getNature());
            equipementToUpdate.setMarque(equipement.getMarque());
            equipementToUpdate.setModele(equipement.getModele());
            equipementToUpdate.setNSerie(equipement.getNSerie());
            equipementToUpdate.setStatutEquipement(equipement.getStatutEquipement());
            equipementToUpdate.setUtilisateur(equipement.getUtilisateur());
            equipementToUpdate.setDirection(equipement.getDirection());
            equipementToUpdate.setDatePos(equipement.getDatePos());
            return listeEquipementRepository.save(equipementToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEquipementParAgent(Long idEquipementParAgent) {
        if (listeEquipementRepository.existsById(idEquipementParAgent)) {
            listeEquipementRepository.deleteById(idEquipementParAgent);
        }
    }

    @Override
    public void affecterAgentAEquipement(Long idEquipement, Long matricule) {
        Optional<ListeEquipementsParAgent> equipementOpt = listeEquipementRepository.findById(idEquipement);
        if (equipementOpt.isPresent()) {
            ListeEquipementsParAgent equipement = equipementOpt.get();
            equipement.setUtilisateur(String.valueOf(matricule));
            listeEquipementRepository.save(equipement);
        }

    }

    @Override
    public List<ListeEquipementsParAgent> getEquipementsDisponibles() {
        return listeEquipementRepository.findByStatutEquipement("DISPONIBLE");
    }

    @Override
    public ListeEquipementsParAgent changerStatutEquipement(Long idEquipement, String nouveauStatut) {
        Optional<ListeEquipementsParAgent> equipementOpt = listeEquipementRepository.findById(idEquipement);
        if (equipementOpt.isPresent()) {
            ListeEquipementsParAgent equipement = equipementOpt.get();
            equipement.setStatutEquipement(nouveauStatut);
            return listeEquipementRepository.save(equipement);
        }
        return null;
    }

    @Override
    public List<ListeEquipementsParAgent> findByMarque(String marque) {
        return listeEquipementRepository.findByMarque(marque);

    }
}
