package com.dorjear.training.loyalty.offers.api;

import com.dorjear.training.loyalty.offers.service.OfferService;
import com.dorjear.training.loyalty.api.ResourceNotFoundException;
import com.dorjear.training.loyalty.model.OfferCategory;
import com.dorjear.training.loyalty.offers.model.Offer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController implements OfferApi {

  private final OfferService offerService;

  @Autowired
  OfferController(final OfferService offerService) {
    this.offerService = offerService;
  }

  @Override
  public List<Offer> getOffers(final OfferCategory category) {
    final List<Offer> foundOffers = offerService.getOffersByCategory(category);

    if (foundOffers.isEmpty()) {
      throw new ResourceNotFoundException("No offers exist.");
    }

    return foundOffers;
  }

  @Override
  public Offer getOffer(final Long offerId) {

    final Optional<Offer> foundOffer = offerService.getOffer(offerId);

    if (foundOffer.isEmpty()) {
      throw new ResourceNotFoundException("No offer exists with offerId=" + offerId);
    }

    return foundOffer.get();
  }
}
