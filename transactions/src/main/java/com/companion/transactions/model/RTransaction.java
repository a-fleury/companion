package com.companion.transactions.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import jakarta.validation.constraints.AssertTrue;

@Entity(name = "r_transaction")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private UUID userId;

    @NotNull
    @PositiveOrZero
    private Double amountR;

    @NotNull
    @PositiveOrZero
    private int amountV;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RTransactionType type;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RTransactionStatus status;

    @ManyToOne
    private WithdrawalRate withdrawalRate;

    @ManyToOne
    private ShopOffer shopOffer;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Optional<WithdrawalRate> getWithdrawalRate() {
        return Optional.ofNullable(withdrawalRate);
    }

    public Optional<ShopOffer> getShopOffer() {
        return Optional.ofNullable(shopOffer);
    }


    @AssertTrue(message = "Withdrawal rate ID is required for WITHDRAWAL transactions" +
            " and Shop Offer ID is required for DEPOSIT transactions")
    @SuppressWarnings("unused")
    private boolean isTransactionIdsValid() {
        if (type == RTransactionType.WITHDRAWAL) {
            return isValidWithdrawal();
        } else if (type == RTransactionType.DEPOSIT) {
            return isValidDeposit();
        }
        return true;
    }

    private boolean isValidWithdrawal() {
        return withdrawalRate != null && shopOffer == null;
    }

    private boolean isValidDeposit() {
        return shopOffer != null && withdrawalRate == null;
    }

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

