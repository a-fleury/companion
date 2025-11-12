package com.companion.meetings.controller;
import com.companion.meetings.dto.meeting.CreateMeetingRequest;
import com.companion.meetings.dto.meeting.MeetingPatchRequest;
import com.companion.meetings.events.meeting.MeetingEventPublisher;
import com.companion.meetings.events.meeting.MeetingCreatedEvent;

import com.companion.meetings.model.MeetingStatus;
import com.companion.meetings.service.MeetingService;
import com.companion.meetings.model.Meeting;
import com.companion.meetings.repository.MeetingRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingRepository repo;

    private final MeetingService meetingService;
    private final MeetingEventPublisher eventPublisher;

    public MeetingController(MeetingRepository repo, MeetingService meetingService, MeetingEventPublisher eventPublisher) {
        this.repo = repo;
        this.meetingService = meetingService;
        this.eventPublisher =  eventPublisher;
    }

    // Lister tous les meetings
    @GetMapping
    public List<Meeting> list() {
        return repo.findAll();
    }

    // Récupérer un meeting par id
    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Récupérer tous les meetings par id d'host
    @GetMapping("host/{id}")
    public List<Meeting> getByHostId(@PathVariable Long id) {
        return repo.findByhostUserId(id);
    }

    //Récupérer tous les meetings par id de client
    @GetMapping("client/{id}")
    public List<Meeting> getByClientId(@PathVariable Long id) {
        return repo.findByclientUserId(id);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Meeting> patch(@PathVariable long id, @RequestBody MeetingPatchRequest patchBody) {
        Meeting updated = meetingService.patchMeeting(id, patchBody);
        return ResponseEntity.ok(updated);
    }

    // Supprimer un meeting
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/meetings -> crée un meeting
    @PostMapping
    public ResponseEntity<Meeting> create(@Valid @RequestBody CreateMeetingRequest req) {
        Meeting m = new Meeting();
        m.setDate(req.getDate());
        m.setDuration(req.getDuration());
        m.setStatus(req.getStatus());
        m.setLocation(req.getLocation());
        m.setStartsAt(req.getStartsAt());
        m.setHostUserId(req.getHostUserId());
        m.setClientUserId(req.getClientUserId());
        m.setType(req.getAnnonceType());

        Meeting saved = repo.save(m);

        var evt = new MeetingCreatedEvent(
                java.util.UUID.randomUUID().toString(),
                saved.getId(),
                saved.getHostUserId(),
                saved.getClientUserId(),
                saved.getDuration(),
                saved.getPrice(),
                saved.getStatus()


        );
        eventPublisher.publishCreated(evt);


        // 201 Created + Location header vers la ressource
        return ResponseEntity
                .created(URI.create("/api/meetings/" + saved.getId()))
                .body(saved);
    }



}
