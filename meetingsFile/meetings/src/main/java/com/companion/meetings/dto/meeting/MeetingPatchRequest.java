package com.companion.meetings.dto.meeting;

import com.companion.meetings.model.MeetingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NotNull
public class MeetingPatchRequest {

    private String location;

    private LocalDateTime date;

    private int duration;

    private LocalDateTime startsAt;

    private MeetingStatus status;
    private int price;


}



