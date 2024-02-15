package com.dorjear.training.loyalty.member.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address implements Serializable {
  private String line1;
  private String line2;
  private String city;
  private String postcode;
  private String state;
  private String country;
}
