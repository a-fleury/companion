package com.companion.notification.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.companion.notification.models.InAppNotificationEntity;

public interface InAppNotificationRepository extends JpaRepository<InAppNotificationEntity, Long> {

    public Collection<InAppNotificationEntity> findByUserId(String userId);
    
}
