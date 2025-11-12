package com.companion.meetings.repository;

import com.companion.meetings.model.PropositionOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PropositionOffreRepository extends JpaRepository<PropositionOffre, Long> {

    List<PropositionOffre> findByProposerId(Long ProposerId);
//    List<Meeting> findByhostUserId(Long id);
//    List<Meeting> findbyclientUserId(Long id);

}
