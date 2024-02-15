package com.dorjear.training.loyalty.member.entity;

import com.dorjear.training.loyalty.member.entity.MemberProgramEntity.MemberProgramId;
import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@Table(name = "MEMBER_PROGRAM")
@IdClass(MemberProgramId.class)
public class MemberProgramEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 3711969554522615133L;

  @Id
  @NotNull
  private Long memberId;

  @Id
  @NotNull
  private String programId;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class MemberProgramId implements Serializable {

    @Serial
    private static final long serialVersionUID = -5480459821758533585L;

    @Column(name = "MEMBER_ID", nullable = false)
    private Long memberId;
    @Column(name = "PROGRAM_ID", nullable = false)
    private String programId;
  }
}
