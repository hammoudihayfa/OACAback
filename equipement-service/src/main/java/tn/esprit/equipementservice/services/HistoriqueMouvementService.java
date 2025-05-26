package tn.esprit.equipementservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.HistoriqueMouvement;
import tn.esprit.equipementservice.repositories.HistoriqueMouvementRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class HistoriqueMouvementService {
    @Autowired
    private HistoriqueMouvementRepository mouvementRepository;
    public HistoriqueMouvement addMouvement(HistoriqueMouvement mouvement) {
        return mouvementRepository.save(mouvement);
    }


    public List<HistoriqueMouvement> getAllMouvements() {
        return mouvementRepository.findAll();
    }

    public List<HistoriqueMouvement> getMouvementsByEquipementId(Long equipementId) {
        return mouvementRepository.findByEquipement_IdEquipement(equipementId);
    }

    public List<HistoriqueMouvement> getMouvementsByUtilisateur(String utilisateur) {
        return mouvementRepository.findAll().stream()
                .filter(m -> m.getUtilisateur() != null && m.getUtilisateur().toLowerCase().contains(utilisateur.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<HistoriqueMouvement> filterMouvements(String date, String client, String patrimony) {
        return mouvementRepository.findAll().stream()
                .filter(m -> {
                    boolean matchDate = (date == null || (m.getDateMouvement() != null &&
                            m.getDateMouvement().format(DateTimeFormatter.ISO_LOCAL_DATE).equals(date)));
                    boolean matchClient = (client == null || (m.getUtilisateur() != null &&
                            m.getUtilisateur().toLowerCase().contains(client.toLowerCase())));
                    boolean matchPatrimony = (patrimony == null || (m.getEquipement() != null &&
                            (
                                    (m.getEquipement().getNumeroSerie() != null &&
                                            m.getEquipement().getNumeroSerie().toLowerCase().contains(patrimony.toLowerCase()))
                                            ||
                                            String.valueOf(m.getEquipement().getIdEquipement()).equals(patrimony)
                            )
                    ));

                    return matchDate && matchClient && matchPatrimony;
                })
                .collect(Collectors.toList());
    }


    public List<HistoriqueMouvement> searchMouvements(String searchTerm) {
        String lowerSearchTerm = searchTerm.toLowerCase();

        return mouvementRepository.findAll().stream()
                .filter(m -> {
                    boolean matchType = m.getTypeMouvement() != null &&
                            m.getTypeMouvement().toLowerCase().contains(lowerSearchTerm);
                    boolean matchUser = m.getUtilisateur() != null &&
                            m.getUtilisateur().toLowerCase().contains(lowerSearchTerm);
                    boolean matchComment = m.getCommentaire() != null &&
                            m.getCommentaire().toLowerCase().contains(lowerSearchTerm);
                    boolean matchEquipMarque = m.getEquipement() != null &&
                            m.getEquipement().getMarqueEquipement() != null &&
                            m.getEquipement().getMarqueEquipement().toLowerCase().contains(lowerSearchTerm);
                    boolean matchEquipSerie = m.getEquipement() != null &&
                            m.getEquipement().getNumeroSerie() != null &&
                            m.getEquipement().getNumeroSerie().toLowerCase().contains(lowerSearchTerm);
                    boolean matchDate = m.getDateMouvement() != null &&
                            m.getDateMouvement().format(DateTimeFormatter.ISO_LOCAL_DATE).contains(lowerSearchTerm);

                    return matchType || matchUser || matchComment || matchEquipMarque || matchEquipSerie || matchDate;
                })
                .collect(Collectors.toList());
    }

}
