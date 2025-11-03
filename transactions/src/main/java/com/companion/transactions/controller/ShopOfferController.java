package com.companion.transactions.controller;

import com.companion.transactions.dto.ShopOfferCreateDTO;
import com.companion.transactions.dto.ShopOfferUpdateDTO;
import com.companion.transactions.model.ShopOffer;
import com.companion.transactions.service.ShopOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shop-offers")
@RequiredArgsConstructor
public class ShopOfferController {

    private final ShopOfferService shopOfferService;

    @GetMapping("/")
    public ResponseEntity<List<ShopOffer>> getAllShopOffers() {
        List<ShopOffer> shopOffers = shopOfferService.getAll();
        return ResponseEntity.ok(shopOffers);
    }

    @GetMapping("/active")
    public ResponseEntity<List<ShopOffer>> getAllActiveShopOffers() {
        List<ShopOffer> shopOffers = shopOfferService.getAllActive();
        return ResponseEntity.ok(shopOffers);
    }

    @GetMapping("/:id")
    public ResponseEntity<ShopOffer> getShopOfferById(UUID id) {
        ShopOffer shopOffer = shopOfferService.getById(id);
        return ResponseEntity.ok(shopOffer);
    }

    @PostMapping("/")
    public ResponseEntity<ShopOffer> createShopOffer(ShopOfferCreateDTO dto) {
        ShopOffer shopOffer = shopOfferService.create(dto);
        return ResponseEntity.ok(shopOffer);
    }

    @PatchMapping("/")
    public ResponseEntity<String> updateShopOffer(ShopOfferUpdateDTO dto) {
        shopOfferService.update(dto);
        return ResponseEntity.ok("Shop offer updated");
    }

    @PatchMapping("/change-status/:id")
    public ResponseEntity<String> updateShopOfferActive(@Param("id") UUID id) {
        shopOfferService.changeStatus(id);
        return ResponseEntity.ok("Shop offer status updated");
    }

    @DeleteMapping("/:id")
    public ResponseEntity<String> deleteShopOffer(@Param("id") UUID id) {
        shopOfferService.delete(id);
        return ResponseEntity.ok("Shop offer deleted");
    }

}
