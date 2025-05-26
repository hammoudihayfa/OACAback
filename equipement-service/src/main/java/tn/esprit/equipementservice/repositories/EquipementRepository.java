package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.equipementservice.entities.EquipementInformatique;

import java.util.List;

@Repository
public interface EquipementRepository extends JpaRepository<EquipementInformatique, Long> {
    EquipementInformatique findByNumeroSerie(String numeroSerie);
    List<EquipementInformatique> findByModeleEquipementContainingIgnoreCase(String modele);
    long countByEtatIgnoreCase(String etat);
    @Query("SELECT e FROM EquipementInformatique e " +
            "WHERE (:location IS NULL OR e.localisation = :location) " +
            "AND (:type IS NULL OR e.typeEquipement = :type) " +
            "AND (:year IS NULL OR FUNCTION('YEAR', e.dateMiseEnService) = :year)")
    List<EquipementInformatique> findByFilters(String location, String type, Integer year);
}
