package com.companion.transactions.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "v_transaction")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private UUID buyerId;

    @NotNull
    private UUID sellerId;

    @PositiveOrZero
    private int amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private VTransactionType type;

    @Enumerated(EnumType.STRING)
    @NotNull
    private VTransactionStatus status;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // 🔹 Called once before the entity is inserted
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 🔹 Called each time before the entity is updated
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

