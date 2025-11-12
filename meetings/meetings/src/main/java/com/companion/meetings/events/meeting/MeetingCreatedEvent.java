package com.companion.meetings.events.meeting;

import com.companion.meetings.model.MeetingStatus;

public record MeetingCreatedEvent(
        String eventId,
        Long meetingId,
        Long hostUserId,
        Long clientUserId,
        int duration,
        int price,
        MeetingStatus status

) {}
