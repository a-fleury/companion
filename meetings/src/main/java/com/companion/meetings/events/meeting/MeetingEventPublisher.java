package com.companion.meetings.events.meeting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MeetingEventPublisher {

    private final KafkaTemplate<String, Object> kafka;
    private final String createdTopic;
    private final String statusChangedTopic;

    public MeetingEventPublisher(
            KafkaTemplate<String, Object> kafka,
            @Value("${meetings.topics.created}") String createdTopic,
            @Value("${meetings.topics.statusChanged}") String statusChangedTopic) {
        this.kafka = kafka;
        this.createdTopic = createdTopic;
        this.statusChangedTopic = statusChangedTopic;
    }

    public void publishCreated(MeetingCreatedEvent evt) {
        kafka.send(createdTopic, String.valueOf(evt.meetingId()), evt);
    }

    public void publishStatusChanged(MeetingChangedEvent evt) {
        kafka.send(statusChangedTopic, String.valueOf(evt.meetingId()), evt);
    }
}
