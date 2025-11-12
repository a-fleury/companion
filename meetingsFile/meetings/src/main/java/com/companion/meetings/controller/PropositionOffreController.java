package com.companion.meetings.controller;

import com.companion.meetings.dto.propositionOffre.CreatePropositionOffreRequest;
import com.companion.meetings.dto.propositionOffre.PropositionOffrePatchRequest;
import com.companion.meetings.model.PropositionOffre;
import com.companion.meetings.repository.PropositionOffreRepository;
import com.companion.meetings.service.PropositionOffreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/propositionOffre")
public class PropositionOffreController {

    private final PropositionOffreRepository repo;

    private final PropositionOffreService propositionOffreService;

    public PropositionOffreController(PropositionOffreRepository repo, PropositionOffreService propositionOffreService) {
        this.repo = repo;
        this.propositionOffreService = propositionOffreService;
    }

    // Lister toutes les offres
    @GetMapping
    public List<PropositionOffre> list() {
        return repo.findAll();
    }

    // Récupérer une offre par id
    @GetMapping("/{id}")
    public ResponseEntity<PropositionOffre> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Récupérer tous les meetings par id de la personne faisant l'offre
    @GetMapping("proposer/{id}")
    public List<PropositionOffre> getByProposerId(@PathVariable Long id) {
        return repo.findByProposerId(id);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<PropositionOffre> patch(@PathVariable long id, @RequestBody PropositionOffrePatchRequest patchBody) {
        PropositionOffre updated = propositionOffreService.patchPropositionOffre(id, patchBody);
        return ResponseEntity.ok(updated);
    }

    // Supprimer une offre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // POST  crée une offre
    @PostMapping
    public ResponseEntity<PropositionOffre> create(@Valid @RequestBody CreatePropositionOffreRequest req) {
        PropositionOffre o = new PropositionOffre();
        o.setDate(req.getDate());
        o.setStatus(req.getStatus());
        o.setProposerId(req.getProposerId());
        o.setMeetingId(req.getMeetingId());
        o.setPrice(req.getPrice());

        PropositionOffre saved = repo.save(o);
        // 201 Created + Location header vers la ressource
        return ResponseEntity
                .created(URI.create("/api/propositionOffre/" + saved.getId()))
                .body(saved);
    }



}
