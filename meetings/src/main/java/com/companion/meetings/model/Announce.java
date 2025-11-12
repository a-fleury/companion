package com.companion.meetings.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "annonce")
public class Announce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int price;

    @NotNull
    @Column(length = 200)
    private String description;

    @NotNull
    @Column(length = 200)
    private String title;

    @NotNull
    @Column(length = 200)
    private String location;


    @NotNull
    private List<Disposability> disposability;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "text")
    private AnnonceStatus status;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "text")
    private AnnonceType type;



    @NotNull
    @Column(name = "host_user_id")
    private Long hostUserId;



    // ====== Constructors ======
    public Announce() {}

    public Announce(AnnonceStatus status, String description,
                    List<Disposability> disposability, Long hostUserId, int price, String location, String title) {
        this.status = status;
        this.description = description;
        this.disposability = disposability;
        this.hostUserId = hostUserId;
        this.price = price;
        this.location = location;
        this.title = title;

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
        return "Announce{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", hostUserId=" + hostUserId +
                ", price='" + price + '\'' +
                ", Disposability='" + disposability + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

