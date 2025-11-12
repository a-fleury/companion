package com.companion.meetings.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {
    @Bean
    NewTopic meetingsCreated(@Value("${meetings.topics.created}") String name) {
        return TopicBuilder.name(name).partitions(3).replicas(1).build();
    }

    @Bean
    NewTopic meetingsStatusChanged(@Value("${meetings.topics.statusChanged}") String name) {
        return TopicBuilder.name(name).partitions(3).replicas(1).build();
    }
}
