package com.companion.meetings.dto.propositionOffre;

import com.companion.meetings.model.PropositionsOffreStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public class CreatePropositionOffreRequest {

    private int price;
    @NotNull private PropositionsOffreStatus status;
    @NotNull  private LocalDateTime date;
    @NotNull  private Long ProposerId;
    @NotNull  private Long MeetingId;

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public PropositionsOffreStatus getStatus() { return status; }
    public void setStatus(PropositionsOffreStatus status) { this.status = status; }

    public Long getProposerId() { return ProposerId; }
    public void setProposerId(Long ProposerId) { this.ProposerId = ProposerId; }


    public Long getMeetingId() { return MeetingId; }
    public void setMeetingId(Long MeetingId) { this.MeetingId = MeetingId; }
}
