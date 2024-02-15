package com.dorjear.training.loyalty.offers.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.dorjear.training.loyalty.model.OfferCategory;
import com.dorjear.training.loyalty.offers.entity.OfferEntity;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class OfferRepositoryTest {

  @Autowired private OfferRepository repository;

  @Test
  void shouldFindAllOffers() {
    List<OfferEntity> offers = repository.findAll();
    assertThat(offers).hasSize(7);
  }

  @Test
  void shouldFindOffersByCategory() {
    List<OfferEntity> offers = repository.findByOfferCategory(OfferCategory.CITY);
    assertThat(offers).hasSize(2);
  }

  @Test
  void shouldFindOffersByNullCategory() {
    List<OfferEntity> offers = repository.findByOfferCategory(null);
    assertThat(offers).hasSize(0);
  }
}
