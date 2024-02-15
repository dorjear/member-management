package com.dorjear.training.loyalty.offers.api;

import com.dorjear.training.loyalty.offers.OpenApi;
import com.dorjear.training.loyalty.api.DefaultApiErrorResponses;
import com.dorjear.training.loyalty.model.OfferCategory;
import com.dorjear.training.loyalty.offers.model.Offer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = OpenApi.OFFERS_API_TAG)
@DefaultApiErrorResponses
public interface OfferApi {

  @GetMapping(value = OpenApi.OFFER_URL)
  @Operation(summary = "Retrieve offers",
      description = "This API returns all available offer by default <br>"
          + "It will return offer with provided category when query parameter category is provided"
          + "This API returns the offer information such as id, name and description.")
  @ApiResponse(
      responseCode = "200",
      description = "The response payload contains the offer information.",
      content = @Content(schema = @Schema(implementation = Offer.class)))
  List<Offer> getOffers(@RequestParam(value = "category", required = false) OfferCategory category);

  @GetMapping(value = OpenApi.OFFER_OFFER_ID_URL)
  @Operation(summary = "Retrieve the offer information by offer-id",
      description = "This API returns the offer information such as id, name and description.")
  @ApiResponse(
      responseCode = "200",
      description = "The response payload contains the offer information.",
      content = @Content(schema = @Schema(implementation = Offer.class)))
  Offer getOffer(
      @PathVariable
      @Parameter(name = "offerId", example = "220010") final Long offerId);

}
