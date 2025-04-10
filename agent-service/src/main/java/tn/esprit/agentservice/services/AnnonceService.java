package tn.esprit.agentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.agentservice.entities.Annonce;
import tn.esprit.agentservice.repositories.AnnonceRepository;

import java.time.LocalDate;
import java.util.List;
@Service
public class AnnonceService implements IAnnonce{
    @Autowired
    private AnnonceRepository annonceRepository;

    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    public Annonce getAnnonceById(Long id) {
        return annonceRepository.findById(id).orElse(null);
    }

    public Annonce addAnnonce(Annonce annonce) {
        annonce.setDatePublication(LocalDate.now());
        return annonceRepository.save(annonce);
    }

    public void deleteAnnonce(Long id) {
        annonceRepository.deleteById(id);
    }
}
