package com.companion.meetings.controller;
import com.companion.meetings.dto.announce.AnnouncePatchRequest;
import com.companion.meetings.dto.announce.CreateAnnounceRequest;
import com.companion.meetings.model.Announce;
import com.companion.meetings.repository.AnnounceRepository;
import com.companion.meetings.service.AnnounceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/announce")
public class AnnounceController {


    private final AnnounceRepository repo;

    private final AnnounceService announceService;

    public AnnounceController(AnnounceRepository repo, AnnounceService announceService) {
        this.repo = repo;
        this.announceService = announceService;
    }

    // Lister tous les meetings
    @GetMapping
    public List<Announce> list() {
        return repo.findAll();
    }

    // Récupérer un meeting par id
    @GetMapping("/{id}")
    public ResponseEntity<Announce> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Récupérer tous les meetings par id d'host
    @GetMapping("host/{id}")
    public List<Announce> getByHostId(@PathVariable Long id) {
        return repo.findByhostUserId(id);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<Announce> patch(@PathVariable long id, @RequestBody AnnouncePatchRequest patchBody) {
        Announce updated = announceService.patchAnnounce(id, patchBody);
        return ResponseEntity.ok(updated);
    }

    // Supprimer un meeting
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
//
    // POST /api/announce -> crée une annonce
    @PostMapping
    public ResponseEntity<Announce> create(@Valid @RequestBody CreateAnnounceRequest req) {
        Announce m = new Announce();
        m.setDescription(req.getDescription());
        m.setPrice(req.getPrice());
        m.setTitle(req.getTitle());
        m.setHostUserId(req.getHostUserId());
        m.setDisposability(req.getDisposability());
        m.setLocation(req.getLocation());
        m.setType(req.getType());


        Announce saved = repo.save(m);
        // 201 Created + Location header vers la ressource
        return ResponseEntity
                .created(URI.create("/api/announce/" + saved.getId()))
                .body(saved);
    }



}
