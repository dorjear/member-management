package com.dorjear.training.loyalty.offers.entity;

import com.dorjear.training.loyalty.model.OfferCategory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OFFER")
public class OfferEntity {

  @Id
  @Column(name = "OFFER_ID", updatable = false, nullable = false)
  @NotNull
  private Long offerId;

  @NotNull
  @Column(name = "OFFER_NAME", nullable = false)
  private String offerName;

  @NotNull
  @Column(name = "OFFER_CATEGORY", nullable = false)
  @Enumerated(EnumType.STRING)
  private OfferCategory offerCategory;

  @NotNull
  @Column(name = "OFFER_DESCRIPTION", nullable = false)
  private String offerDescription;

}
