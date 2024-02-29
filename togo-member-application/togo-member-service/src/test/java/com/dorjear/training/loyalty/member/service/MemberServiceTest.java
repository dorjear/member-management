package com.dorjear.training.loyalty.member.service;

import static com.dorjear.training.loyalty.member.TestDatas.MEMBER_ENTITY_0;
import static com.dorjear.training.loyalty.member.TestDatas.MEMBER_ID;
import static com.dorjear.training.loyalty.member.TestDatas.MEMBER_PROGRAM_ENTITIES_2;
import static com.dorjear.training.loyalty.member.TestDatas.PROGRAMS_ENTITIES_1;
import static com.dorjear.training.loyalty.member.TestDatas.PROGRAMS_ENTITIES_2;
import static com.dorjear.training.loyalty.member.TestDatas.PROGRAM_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.dorjear.training.loyalty.api.ResourceNotFoundException;
import com.dorjear.training.loyalty.member.downstream.AgifyClient;
import com.dorjear.training.loyalty.member.repository.MemberProgramRepository;
import com.dorjear.training.loyalty.member.repository.MemberRepository;
import com.dorjear.training.loyalty.member.repository.ProgramRepository;
import com.dorjear.training.loyalty.member.client.OfferClient;
import com.dorjear.training.loyalty.member.model.Member;
import com.dorjear.training.loyalty.model.AccountStatus;
import com.dorjear.training.loyalty.model.OfferCategory;
import com.dorjear.training.loyalty.offers.model.Offer;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @Mock
  MemberRepository memberRepository;

  @Mock
  ProgramRepository programRepository;

  @Mock
  MemberProgramRepository memberProgramRepository;

  @Mock
  AgifyClient agifyClient;

  @Mock
  OfferClient offerClient;

  MemberService memberService;

  @BeforeEach
  void beforeEach() {
    memberService = new MemberService(
        memberRepository,
        programRepository,
        memberProgramRepository,
        agifyClient,
        offerClient
    );
  }

  @Test
  void getMember_succeffullyGetsMemberEntityAndConvertsToMemberModel() {

    final List<Offer> offers = List.of(Offer.builder()
        .id(3344L)
        .name("Autumn Leaves")
        .description("See the autumn leaves of Canada")
        .build());

    when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.of(MEMBER_ENTITY_0));
    when(offerClient.getOffers(OfferCategory.NATURE)).thenReturn(offers);
    when(memberProgramRepository.findAllByMemberId(MEMBER_ID)).thenReturn(MEMBER_PROGRAM_ENTITIES_2);
    when(programRepository.findAllById(anyIterable())).thenReturn(PROGRAMS_ENTITIES_2);

    final Optional<Member> actual = memberService.getMember(MEMBER_ID);

    assertThat(actual)
        .isNotNull()
            .isPresent();

    final Member member = actual.get();

    assertThat(member.getMemberId()).isEqualTo(MEMBER_ID);
    assertThat(member.getAccountStatus()).isEqualTo(AccountStatus.ACTIVE);
  }

  @Test
  public void enrollProgramSuccessful(){
    when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.of(MEMBER_ENTITY_0));
    when(programRepository.findByProgramIdIn(PROGRAM_ID)).thenReturn(PROGRAMS_ENTITIES_2);
    memberService.enrollProgram(MEMBER_ID, PROGRAM_ID);
    Mockito.verify(memberProgramRepository,times(1)).saveAll(any());
  }

  @Test
  public void enrolMemberExceptionMemberIdNotFound(){
    when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.empty());
    ResourceNotFoundException exception=assertThrows(ResourceNotFoundException.class, ()-> memberService.enrollProgram(MEMBER_ID, PROGRAM_ID));
    assertThat(exception.getMessage()).isEqualTo("Member with memberId=[1] not found");
  }

  @Test
  public void enrolMemberExceptionProgramIdsNotValid(){
    when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.of(MEMBER_ENTITY_0));
    when(programRepository.findByProgramIdIn(PROGRAM_ID)).thenReturn(PROGRAMS_ENTITIES_1);

    ResourceNotFoundException exception=assertThrows(ResourceNotFoundException.class, ()-> memberService.enrollProgram(MEMBER_ID, PROGRAM_ID));
    assertThat(exception.getMessage()).isEqualTo("Not all the program ids are valid.");
  }

}
