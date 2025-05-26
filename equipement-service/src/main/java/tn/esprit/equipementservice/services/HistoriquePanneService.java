package tn.esprit.equipementservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.entities.HistoriquePanne;
import tn.esprit.equipementservice.repositories.HistoriquePanneRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoriquePanneService {

    @Autowired
    private HistoriquePanneRepository historiqueRepo;

    public List<HistoriquePanne> getHistoriqueByEquipement(Long idEquipement) {
        return historiqueRepo.findByEquipementPanne_IdEquipementEnPanneOrderByDateAsc(idEquipement);
    }

    public void ajouterEtape(EquipementEnPanne equipement, String etat, String commentaire, String acteur) {
        HistoriquePanne h = new HistoriquePanne();
        h.setDate(LocalDateTime.now());
        h.setEtat(etat);
        h.setCommentaire(commentaire);
        h.setEquipementPanne(equipement);
        h.setActeur(acteur);
        historiqueRepo.save(h);
    }

}

