package com.dorjear.training.loyalty.offers;

import com.dorjear.training.loyalty.offers.model.Offer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestDatas {
  public static final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

  public static final List<Offer> OFFERS_ALL;
  public static final List<Offer> OFFERS_CITY;

  static {
    try {
      OFFERS_ALL =
          Arrays.stream(
                  objectMapper.readValue(
                      TestDatas.class.getResourceAsStream("/offers-all.json"), Offer[].class))
              .toList();
      OFFERS_CITY =
          Arrays.stream(
                  objectMapper.readValue(
                      TestDatas.class.getResourceAsStream("/offers-city.json"), Offer[].class))
              .toList();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
