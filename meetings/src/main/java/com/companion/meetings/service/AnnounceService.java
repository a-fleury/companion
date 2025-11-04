package com.companion.meetings.service;

import com.companion.meetings.dto.announce.AnnouncePatchRequest;
import com.companion.meetings.exception.ResourceNotFoundException;
import com.companion.meetings.model.Announce;
import com.companion.meetings.repository.AnnounceRepository;
import org.springframework.stereotype.Service;


@Service
public class AnnounceService {

    private final AnnounceRepository announceRepository;

    public AnnounceService(AnnounceRepository announceRepository) {
        this.announceRepository = announceRepository;
    }


    public Announce patchAnnounce(Long id, AnnouncePatchRequest patchAnnounce) {

        // 🔹 1. On charge l’entité existante
        Announce announce = announceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        // 🔹 2. On met à jour seulement les champs non nuls


        if (patchAnnounce.getLocation() != null) {
            announce.setLocation(patchAnnounce.getLocation());
        }

        if (patchAnnounce.getTitle() != null) {
            announce.setTitle(patchAnnounce.getTitle());
        }
        if (patchAnnounce.getDescription() != null) {
            announce.setDescription(patchAnnounce.getDescription());
        }
        if(patchAnnounce.getPrice() !=0) {
            announce.setPrice(patchAnnounce.getPrice());
        }
        if(patchAnnounce.getStatus()!=null) {
            announce.setStatus(patchAnnounce.getStatus());
        }
        if(patchAnnounce.getDisposability()!=null) {
            announce.setDisposability(patchAnnounce.getDisposability());
        }




        // 🔹 3. Hibernate détecte un objet déjà existant (id != null) → exécute un UPDATE
        Announce updatedAnnounce = announceRepository.save(announce);

        // 🔹 4. Conversion en DTO pour la réponse
        return mapAnnounce(updatedAnnounce);
    }

    private Announce mapAnnounce(Announce updatedAnnounce) {
        Announce announce = new Announce();
        announce.setId(updatedAnnounce.getId());
        announce.setLocation(updatedAnnounce.getLocation());
        announce.setTitle(updatedAnnounce.getTitle());
        announce.setDescription(updatedAnnounce.getDescription());
        announce.setPrice(updatedAnnounce.getPrice());
        announce.setDisposability(updatedAnnounce.getDisposability());
        announce.setStatus(updatedAnnounce.getStatus());

        return announce;
    }



}
