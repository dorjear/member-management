package com.dorjear.training.loyalty.member.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
    "programId",
    "marketingName",
    "summaryDescription"
})
public class Program {

  @Size(max = 8)
  private String programId;

  @Size(max = 20)
  private String marketingName;

  @Size(max = 200)
  private String summaryDescription;
}
