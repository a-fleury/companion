package com.companion.transactions.service;

import com.companion.transactions.dto.ShopOfferCreateDTO;
import com.companion.transactions.dto.ShopOfferUpdateDTO;
import com.companion.transactions.model.ShopOffer;
import com.companion.transactions.repository.ShopOfferRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopOfferService {

    private final ShopOfferRepository shopOfferRepository;

    public List<ShopOffer> getAll() {
        return shopOfferRepository.findAll();
    }

    public List<ShopOffer> getAllActive() {
        return shopOfferRepository.findAllByActiveTrue();
    }

    public ShopOffer getById(UUID id) {
        return shopOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop offer not found: " + id));
    }

    public ShopOffer create(ShopOfferCreateDTO dto) {
        ShopOffer shopOffer = ShopOffer.builder()
                .description(dto.getDescription())
                .price(dto.getPrice())
                .amountV(dto.getAmountV())
                .active(dto.isActive())
                .bonus(dto.getBonus())
                .discount(dto.getDiscount())
                .build();

        return shopOfferRepository.save(shopOffer);
    }

     public void update(ShopOfferUpdateDTO dto) {
        ShopOffer shopOffer = getById(dto.getId());

        shopOffer.setDescription(dto.getDescription());
        shopOffer.setPrice(dto.getPrice());
        shopOffer.setAmountV(dto.getAmountV());
        shopOffer.setActive(dto.isActive());
        shopOffer.setBonus(dto.getBonus());
        shopOffer.setDiscount(dto.getDiscount());

        shopOfferRepository.save(shopOffer);
     }

    public void changeStatus(UUID id) {
        ShopOffer shopOffer = getById(id);
        shopOffer.setActive(!shopOffer.isActive());
        shopOfferRepository.save(shopOffer);
    }


    public void delete(UUID id) {
        ShopOffer shopOffer = getById(id);
        shopOfferRepository.delete(shopOffer);
    }
}
