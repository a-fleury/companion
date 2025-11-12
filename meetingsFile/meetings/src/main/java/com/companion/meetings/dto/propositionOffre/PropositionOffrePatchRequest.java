package com.companion.meetings.dto.propositionOffre;

import com.companion.meetings.model.PropositionsOffreStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@NotNull
public class PropositionOffrePatchRequest {

    @NotNull private Long meetingId;

    @NotNull private Long ProposerId;

    @NotNull private LocalDateTime date;

    @NotNull private PropositionsOffreStatus status;

    @NotNull private int price;

}



