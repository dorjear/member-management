package com.dorjear.training.loyalty.member.downstream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class AgifyClient {

  private final String baseUrl;
  private final RestTemplate restTemplate;

  @Autowired
  public AgifyClient(final RestTemplate restTemplate, @Value("${downstream.agify.basePath}") final String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }

  public int getAgeByFirstName(final String firstName) {
    String uriAge = baseUrl + firstName;
    AgifyResponse resultForAge = restTemplate.getForObject(uriAge, AgifyResponse.class);
    return resultForAge.getAge();
  }
}
