package com.companion.meetings.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 200)
    private LocalDateTime date;

    @NotNull
    private int duration;

    @NotNull
    private int price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "text")
    private MeetingStatus status;

    @NotNull
    @Column(length = 200)
    private String location;

    @NotNull
    @Column(name = "starts_at")
    private LocalDateTime startsAt;

    @NotNull
    @Column(name = "host_user_id")
    private Long hostUserId;

    @NotNull
    @Column(name = "clientUserId")
    private Long clientUserId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "text")
    private AnnonceType type;

    // ====== Constructors ======
    public Meeting() {}

    public Meeting(LocalDateTime date, int duration, MeetingStatus status, String location,
                   LocalDateTime startsAt, Long hostUserId, Long clientUserId,int price) {
        this.date = date;
        this.duration = duration;
        this.status = status;
        this.location = location;
        this.startsAt = startsAt;
        this.hostUserId = hostUserId;
        this.clientUserId = clientUserId;
        this.price = price;
    }


    // ====== equals / hashCode (basés sur id) ======
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meeting)) return false;
        Meeting that = (Meeting) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // ====== toString ======
    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                ", status=" + status +
                ", location='" + location + '\'' +
                ", startsAt=" + startsAt +
                ", hostUserId=" + hostUserId +
                ", clientUserId=" + clientUserId +
                '}';
    }
}
