package com.companion.meetings.repository;

import java.util.List;
import com.companion.meetings.model.Meeting;
import com.companion.meetings.model.MeetingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findByStatus(MeetingStatus status);
    List<Meeting> findByhostUserId(Long id);
    List<Meeting> findByclientUserId(Long id);

}
