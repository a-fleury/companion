package com.companion.meetings.dto.meeting;

import com.companion.meetings.model.AnnonceType;
import com.companion.meetings.model.MeetingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NotNull
public class CreateMeetingRequest {
    private LocalDateTime date;
    private int duration;
    private MeetingStatus status;
    private String location;
    private LocalDateTime startsAt;
    private Long hostUserId;
    private Long clientUserId;
    private AnnonceType annonceType;
}
