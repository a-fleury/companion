package com.companion.meetings.dto.announce;

import com.companion.meetings.model.AnnonceStatus;
import com.companion.meetings.model.AnnonceType;
import com.companion.meetings.model.Disposability;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NotNull
public class CreateAnnounceRequest {

    private int price;
    private String description;
    private String location;
    private List<Disposability> disposability;
    private String title;
    private AnnonceStatus status;
    private Long hostUserId;
    private AnnonceType type;

}

