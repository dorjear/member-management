package com.dorjear.training.loyalty.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "PROGRAM")
public class ProgramEntity {

  @Id
  @Column(name = "PROGRAM_ID", updatable = false, nullable = false)
  @NotNull
  private String programId;

  @NotNull
  @Column(name = "PROGRAM_NAME", nullable = false)
  private String programName;

  @NotNull
  @Column(name = "PROGRAM_DESCRIPTION", nullable = false)
  private String programDescription;

}
