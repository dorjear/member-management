package com.dorjear.training.loyalty.member.entity;

import com.dorjear.training.loyalty.member.model.Address;
import com.dorjear.training.loyalty.model.AccountStatus;
import com.dorjear.training.loyalty.model.OfferCategory;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
@SequenceGenerator(name="MEMBER_ID_SEQUENCE", allocationSize = 100)
@TypeDef(name = "json", typeClass = JsonType.class)
public class MemberEntity implements Serializable {

  @Id
  @GeneratedValue(generator = "MEMBER_ID_SEQUENCE")
  @Column(name = "MEMBER_ID", updatable = false, nullable = false)
  @NotNull
  private Long memberId;

  @NotNull
  @Column(name = "ACCOUNT_STATUS", nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountStatus accountStatus;

  @NotNull
  @Column(name = "GIVEN_NAME", nullable = false)
  private String givenName;

  @NotNull
  @Column(name = "SURNAME", nullable = false)
  private String surname;

  @NotNull
  @Column(name = "ENROLLED_SINCE", nullable = false)
  private LocalDate enrolledSince;

  @NotNull
  @Column(name = "PREFERENCE", nullable = false)
  @Enumerated(EnumType.STRING)
  private OfferCategory offerCategoryPreference;

  // Address is a column with type JSON within Member rather than a linked table
  @Type(type = "json")
  @Column(columnDefinition = "json")
  private Address address;
}
