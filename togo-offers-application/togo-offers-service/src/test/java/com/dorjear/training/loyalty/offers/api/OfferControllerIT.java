package com.dorjear.training.loyalty.offers.api;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.dorjear.training.loyalty.offers.model.Offer;

import static com.dorjear.training.loyalty.offers.TestDatas.OFFERS_ALL;
import static com.dorjear.training.loyalty.offers.TestDatas.OFFERS_CITY;
import static com.dorjear.training.loyalty.offers.TestDatas.objectMapper;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * This test is to demo a different testing method to the MemberControllerIT.
 * This test is to test with real bean and real in-memory database.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OfferControllerIT {

  @LocalServerPort
  private int port;

  private String baseUrl = "http://localhost";

  private static RestTemplate restTemplate = null;

  @BeforeAll
  public static void init() {
    restTemplate = new RestTemplate();
  }

  @BeforeEach
  public void setUp() {
    baseUrl = baseUrl + ":" + port + "/offer";
  }

  @Test
  void getAllOffers() {
    final ResponseEntity<List<Offer>> response=restTemplate.exchange(baseUrl, HttpMethod.GET,null, new ParameterizedTypeReference<>() {});
    final List<Offer> offers=response.getBody();
    assertThat(offers).isEqualTo(OFFERS_ALL);
  }

  @Test
  void getOffersByCategory() throws JsonProcessingException {
    final URI uri=UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("category","CITY").build().toUri();
    final ResponseEntity<List<Offer>> response=restTemplate.exchange(uri, HttpMethod.GET,null, new ParameterizedTypeReference<>() {});
    final List<Offer> offers=response.getBody();
    System.out.println(objectMapper.writeValueAsString(offers));

    assertThat(offers).isEqualTo(OFFERS_CITY);
  }
}