package tn.esprit.agentservice.services;

import tn.esprit.agentservice.entities.Annonce;

import java.util.List;

public interface IAnnonce {
   List<Annonce> getAllAnnonces() ;
     Annonce getAnnonceById(Long idAnnonce) ;

     Annonce addAnnonce(Annonce annonce) ;

     void deleteAnnonce(Long idAnnonce);
}
