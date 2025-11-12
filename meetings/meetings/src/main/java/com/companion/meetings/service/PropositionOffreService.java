package com.companion.meetings.service;

import com.companion.meetings.dto.propositionOffre.PropositionOffrePatchRequest;
import com.companion.meetings.exception.ResourceNotFoundException;
import com.companion.meetings.model.PropositionOffre;
import com.companion.meetings.repository.PropositionOffreRepository;
import org.springframework.stereotype.Service;

@Service
public class PropositionOffreService {

    private final PropositionOffreRepository propositionOffreRepository;

    public PropositionOffreService(PropositionOffreRepository propositionOffreRepository) {
        this.propositionOffreRepository = propositionOffreRepository;
    }

    public PropositionOffre patchPropositionOffre(Long id, PropositionOffrePatchRequest patchOffre) {

        // 🔹 1. On charge l’entité existante
        PropositionOffre offre = propositionOffreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        // 🔹 2. On met à jour seulement les champs non nuls


        if (patchOffre.getPrice() != 0) {
            offre.setPrice(patchOffre.getPrice());
        }



        // 🔹 3. Hibernate détecte un objet déjà existant (id != null) → exécute un UPDATE
        PropositionOffre updatedOffre = propositionOffreRepository.save(offre);

        // 🔹 4. Conversion en DTO pour la réponse
        return mapOffre(updatedOffre);
    }

    private PropositionOffre mapOffre(PropositionOffre OffreUpdated) {
        PropositionOffre offre = new PropositionOffre();
        offre.setId(OffreUpdated.getId());
        offre.setMeetingId(OffreUpdated.getMeetingId());
        offre.setProposerId(OffreUpdated.getProposerId());
        offre.setStatus(OffreUpdated.getStatus());
        offre.setDate(OffreUpdated.getDate());
        offre.setPrice(OffreUpdated.getPrice());
        return offre;
    }

}
