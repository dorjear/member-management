package com.dorjear.training.loyalty.offers.model;

import com.dorjear.training.loyalty.model.OfferCategory;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
    "id",
    "name",
    "category",
    "description"
})
public class Offer {

  @NotNull
  private Long id;

  @Size(max = 20)
  private String name;

  @Size(max = 20)
  private OfferCategory category;

  @Size(max = 200)
  private String description;
}
