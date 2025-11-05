package com.companion.meetings.service;

import com.companion.meetings.dto.meeting.MeetingPatchRequest;
import com.companion.meetings.model.Meeting;
import com.companion.meetings.events.meeting.MeetingEventPublisher;
import com.companion.meetings.events.meeting.MeetingChangedEvent;
import com.companion.meetings.model.MeetingStatus;
import com.companion.meetings.repository.MeetingRepository;
import com.companion.meetings.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingEventPublisher eventPublisher;

    public MeetingService(MeetingRepository meetingRepository, MeetingEventPublisher eventPublisher) {
        this.meetingRepository = meetingRepository;
        this.eventPublisher = eventPublisher;
    }


    public Meeting patchMeeting(Long id, MeetingPatchRequest patchMeeting) {

        // 🔹 1. On charge l’entité existante
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        // 🔹 2. On met à jour seulement les champs non nuls

        var oldStatus = meeting.getStatus();


        if (patchMeeting.getLocation() != null) {
            meeting.setLocation(patchMeeting.getLocation());
        }

        if (patchMeeting.getDate() != null) {
            meeting.setDate(patchMeeting.getDate());
        }

        if (patchMeeting.getDuration() != 0) {
            meeting.setDuration(patchMeeting.getDuration());
        }

        if (patchMeeting.getStartsAt() != null) {
            meeting.setStartsAt(patchMeeting.getStartsAt());
        }

        if (patchMeeting.getStatus() != null) {
            meeting.setStatus(patchMeeting.getStatus());
        }

        if (patchMeeting.getPrice() != 0) {
            meeting.setPrice(patchMeeting.getPrice());
        }


        Meeting updatedMeeting = meetingRepository.save(meeting);

        if (patchMeeting.getStatus() != null && oldStatus != patchMeeting.getStatus()) {
            if(oldStatus == MeetingStatus.ACCEPTED) {
                var evt = new MeetingChangedEvent(
                        java.util.UUID.randomUUID().toString(),
                        updatedMeeting.getId(),
                        updatedMeeting.getHostUserId(),
                        updatedMeeting.getClientUserId(),
                        updatedMeeting.getDuration(),
                        updatedMeeting.getPrice(),
                        updatedMeeting.getStatus()
                );
                eventPublisher.publishStatusAcceptedChanged(evt);
            }
            if(oldStatus == MeetingStatus.PENDING) {
                var evt = new MeetingChangedEvent(
                        java.util.UUID.randomUUID().toString(),
                        updatedMeeting.getId(),
                        updatedMeeting.getHostUserId(),
                        updatedMeeting.getClientUserId(),
                        updatedMeeting.getDuration(),
                        updatedMeeting.getPrice(),
                        updatedMeeting.getStatus()
                );
                eventPublisher.publishStatusPendingChanged(evt);
            }

            if(oldStatus == MeetingStatus.DECLINED) {
                var evt = new MeetingChangedEvent(
                        java.util.UUID.randomUUID().toString(),
                        updatedMeeting.getId(),
                        updatedMeeting.getHostUserId(),
                        updatedMeeting.getClientUserId(),
                        updatedMeeting.getDuration(),
                        updatedMeeting.getPrice(),
                        updatedMeeting.getStatus()
                );
                eventPublisher.publishStatusDeclinedChanged(evt);
            }
            if(oldStatus == MeetingStatus.FINISHED) {
                var evt = new MeetingChangedEvent(
                        java.util.UUID.randomUUID().toString(),
                        updatedMeeting.getId(),
                        updatedMeeting.getHostUserId(),
                        updatedMeeting.getClientUserId(),
                        updatedMeeting.getDuration(),
                        updatedMeeting.getPrice(),
                        updatedMeeting.getStatus()
                );
                eventPublisher.publishStatusFinishedChanged(evt);
            }




        }

        // 🔹 4. Conversion en DTO pour la réponse
        return mapMeeting(updatedMeeting);
    }

    private Meeting mapMeeting(Meeting meetingUpdated) {
        Meeting meeting = new Meeting();
        meeting.setId(meetingUpdated.getId());
        meeting.setLocation(meetingUpdated.getLocation());
        meeting.setDuration(meetingUpdated.getDuration());
        meeting.setStatus(meetingUpdated.getStatus());
        meeting.setStartsAt(meetingUpdated.getStartsAt());
        meeting.setPrice(meetingUpdated.getPrice());
        return meeting;
    }



}
