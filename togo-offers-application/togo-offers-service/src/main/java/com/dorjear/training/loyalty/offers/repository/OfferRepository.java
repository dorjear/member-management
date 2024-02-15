package com.dorjear.training.loyalty.offers.repository;

import com.dorjear.training.loyalty.model.OfferCategory;
import com.dorjear.training.loyalty.offers.entity.OfferEntity;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<OfferEntity, Long> {

  @Override
  List<OfferEntity> findAll();

  List<OfferEntity> findByOfferCategory(OfferCategory offerCategory);

}
