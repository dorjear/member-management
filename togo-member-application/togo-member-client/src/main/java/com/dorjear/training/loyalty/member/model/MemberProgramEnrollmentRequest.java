package com.dorjear.training.loyalty.member.model;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberProgramEnrollmentRequest {
  @Size(max = 8)
  private List<String> programIds;
}
