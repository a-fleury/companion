package com.companion.transactions.repository;

import com.companion.transactions.model.ShopOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ShopOfferRepository extends JpaRepository<ShopOffer, UUID> {
    List<ShopOffer> findAllByActiveTrue();
}
