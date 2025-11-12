package com.companion.meetings.events.meeting;

import com.companion.meetings.model.MeetingStatus;

public record MeetingChangedEvent(
        String eventId,
        Long meetingId,
        Long hostUserId,
        Long clientUserId,
        int duration,
        int price,
        MeetingStatus status

) {}
