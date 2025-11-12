package com.companion.meetings.repository;

import com.companion.meetings.model.Announce;
import com.companion.meetings.model.Meeting;
import com.companion.meetings.model.MeetingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnounceRepository extends JpaRepository<Announce, Long>  {
    List<Announce> findByStatus(Announce status);
    List<Announce> findByhostUserId(Long id);

}


