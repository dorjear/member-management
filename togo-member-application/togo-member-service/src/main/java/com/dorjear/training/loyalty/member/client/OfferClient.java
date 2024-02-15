package com.dorjear.training.loyalty.member.client;

import com.dorjear.training.loyalty.offers.api.OfferApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "togo-offers-client",
    url = "${feign.togo-offers-client.url}")
public interface OfferClient extends OfferApi {

}
