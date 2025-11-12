package com.companion.transactions.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
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
    private Long buyerId;

    @NotNull
    private Long sellerId;

    @NotNull
    private Long meetingId;

    @Nullable
    private UUID ratingId;

    @NotNull
    @PositiveOrZero
    private Integer amount;

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


    @AssertTrue(message = "Meeting ID is required for PURCHASE transactions" +
            " and Rating ID is also required for TIP transactions")
    @SuppressWarnings("unused")
    private boolean isTransactionIdsValid() {
        if (type == VTransactionType.PURCHASE) {
            return isValidPurchase();
        } else if (type == VTransactionType.TIP) {
            return isValidTip();
        }
        return true;
    }

    private boolean isValidPurchase() {
        return meetingId != null && ratingId == null;
    }

    private boolean isValidTip() {
        return meetingId != null && ratingId != null;
    }
}

