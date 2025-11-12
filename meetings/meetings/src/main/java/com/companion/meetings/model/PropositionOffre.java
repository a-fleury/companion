package com.companion.meetings.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;



@Entity
@Setter
@Getter
@Table(name = "PropositionOffre")
public class PropositionOffre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long meetingId;

    @NotNull
    @Column(length = 200)
    private LocalDateTime date;


    @NotNull
    private int price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "text")
    private PropositionsOffreStatus status;

    @NotNull
    @Column(name = "ProposerId")
    private Long proposerId;



    // ====== Constructors ======
    public PropositionOffre() {}

    public PropositionOffre(LocalDateTime date, PropositionsOffreStatus status,int price,Long proposerId, Long meetingId) {
        this.date = date;
        this.price = price;
        this.status = status;
        this.proposerId = proposerId;
        this.meetingId = meetingId;
    }


    // ====== equals / hashCode (basés sur id) ======
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meeting)) return false;
        Meeting that = (Meeting) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // ====== toString ======
    @Override
    public String toString() {
        return "PropositionOffre{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", status=" + status +
                ", meetingId='" + meetingId + '\'' +
                ", proposerId=" + proposerId +
                '}';
    }


}
