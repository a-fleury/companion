package com.companion.transactions.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WithdrawalRate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @PositiveOrZero
    private Integer minAmountV;

    @NotNull
    @PositiveOrZero
    // Divide the amount of virtual money by rate to get the amount in real currency
    private Double rate;

    @Nullable
    private String description;

    @NotNull
    private Boolean active;

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
