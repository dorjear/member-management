package com.dorjear.training.loyalty.offers.service;

import com.dorjear.training.loyalty.offers.entity.OfferEntity;
import com.dorjear.training.loyalty.offers.repository.OfferRepository;
import com.dorjear.training.loyalty.model.OfferCategory;
import com.dorjear.training.loyalty.offers.model.Offer;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OfferService {

  private OfferRepository offerRepository;

  @Autowired
  public OfferService(final OfferRepository offerRepository) {
    this.offerRepository = offerRepository;
  }

  public Optional<Offer> getOffer(final Long offerId) {

    log.info("Finding a offer by id '{}'", offerId);

    final Optional<OfferEntity> optionalFoundOfferEntity = offerRepository.findById(offerId);

    if (optionalFoundOfferEntity.isEmpty()) {
      return Optional.empty();
    }

    log.info("Found a offer by id '{}'", offerId);

    final OfferEntity offerEntity = optionalFoundOfferEntity.get();

    return Optional.of(daoToDto(offerEntity));
  }

  /**
   * Get offers by offerCategory
   * @param category offerCategory to search
   * @return list of offers with the provided category
   */
  public List<Offer> getOffersByCategory(OfferCategory category) {

    log.info("Finding offers by category");

    // findByOfferCategory(null) will search for all offers that doesn't have a category.
    // Category won't be null in the current schema definition.
    // In the current context null category as a parameter means searching for all offers.
    final List<OfferEntity> foundOffers = category!=null ? offerRepository.findByOfferCategory(category) : offerRepository.findAll();

    log.info("Found '{}' offers", foundOffers.size());

    return foundOffers.stream().map(this::daoToDto).toList();
  }

  private Offer daoToDto(OfferEntity offerEntity) {
    return Offer.builder()
            .id(offerEntity.getOfferId())
            .name(offerEntity.getOfferName())
            .category(offerEntity.getOfferCategory())
            .description(offerEntity.getOfferDescription())
            .build();
  }
}
