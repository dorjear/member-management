package com.dorjear.training.loyalty.member.service;

import com.dorjear.training.loyalty.member.downstream.AgifyClient;
import com.dorjear.training.loyalty.member.repository.MemberProgramRepository;
import com.dorjear.training.loyalty.member.repository.MemberRepository;
import com.dorjear.training.loyalty.member.repository.ProgramRepository;
import com.dorjear.training.loyalty.api.ResourceNotFoundException;
import com.dorjear.training.loyalty.member.client.OfferClient;
import com.dorjear.training.loyalty.member.entity.MemberEntity;
import com.dorjear.training.loyalty.member.entity.MemberProgramEntity;
import com.dorjear.training.loyalty.member.entity.ProgramEntity;
import com.dorjear.training.loyalty.member.model.Member;
import com.dorjear.training.loyalty.member.model.Program;
import com.dorjear.training.loyalty.model.AccountStatus;
import com.dorjear.training.loyalty.offers.model.Offer;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberService {

  private static final String FREQUENT_FLYER_PROGRAM = "FF";

  private final MemberRepository memberRepository;

  private final ProgramRepository programRepository;

  private final MemberProgramRepository memberProgramRepository;

  private final OfferClient offerClient;

  private final AgifyClient agifyClient;

  @Autowired
  public MemberService(
      final MemberRepository memberRepository,
      final ProgramRepository programRepository,
      final MemberProgramRepository memberProgramRepository,
      final AgifyClient agifyClient,
      final OfferClient offerClient
  ) {

    this.memberRepository = memberRepository;
    this.programRepository = programRepository;
    this.memberProgramRepository = memberProgramRepository;
    this.agifyClient = agifyClient;
    this.offerClient = offerClient;
  }

  public Member createMember(final Member member) {

    log.info("Creating a member");

    final MemberEntity memberEntity = MemberEntity.builder()
        .accountStatus(AccountStatus.PENDING)
        .givenName(member.getFirstName())
        .surname(member.getLastName())
        .address(member.getAddress())
        .enrolledSince(LocalDate.now())
        .offerCategoryPreference(member.getOfferCategoryPreference())
        .build();

    final MemberEntity savedMember = memberRepository.save(memberEntity);

    log.info("Created a member with id '{}'", savedMember.getMemberId());

    final Optional<ProgramEntity> programToEnrolMemberIn = programRepository.findById(FREQUENT_FLYER_PROGRAM);

    final Set<Program> enrolledPrograms = new HashSet<>();

    if (programToEnrolMemberIn.isPresent()) {
      memberProgramRepository.save(MemberProgramEntity.builder()
          .memberId(memberEntity.getMemberId())
          .programId(programToEnrolMemberIn.get().getProgramId())
          .build());

      enrolledPrograms.add(Program.builder()
          .programId(programToEnrolMemberIn.get().getProgramId())
          .marketingName(programToEnrolMemberIn.get().getProgramName())
          .summaryDescription(programToEnrolMemberIn.get().getProgramDescription())
          .build());
    }

    return Member.builder()
        .memberId(savedMember.getMemberId())
        .accountStatus(savedMember.getAccountStatus())
        .enrolledPrograms(enrolledPrograms)
        .firstName(savedMember.getGivenName())
        .lastName(savedMember.getSurname())
        .memberSince(savedMember.getEnrolledSince())
        .offerCategoryPreference(savedMember.getOfferCategoryPreference())
        .build();
  }

  public Optional<Member> getMember(final Long memberId) {

    log.info("Finding a member by id '{}'", memberId);

    final Optional<MemberEntity> optionalFoundMemberEntity = memberRepository.findById(memberId);

    if (optionalFoundMemberEntity.isEmpty()) {
      return Optional.empty();
    }

    log.info("Found a member by id '{}'", memberId);

    final MemberEntity foundMemberEntity = optionalFoundMemberEntity.get();

    final Set<String> programIds = new HashSet<>();

    final Iterable<MemberProgramEntity> memberProgramEntityS = memberProgramRepository.findAllByMemberId(foundMemberEntity.getMemberId());

    memberProgramEntityS.forEach(mp -> programIds.add(mp.getProgramId()));

    final Set<Program> enrolledPrograms = new HashSet<>();

    programRepository.findAllById(programIds).forEach(p ->
        enrolledPrograms.add(Program.builder()
            .programId(p.getProgramId())
            .marketingName(p.getProgramName())
            .summaryDescription(p.getProgramDescription())
            .build()));

    final List<Offer> offers = offerClient.getOffers(foundMemberEntity.getOfferCategoryPreference());

    final Integer age = agifyClient.getAgeByFirstName(foundMemberEntity.getGivenName());
    log.info("The age is " + age);

    final List<Member.Offer> memberOffers = offers.stream()
        .map(o -> Member.Offer.builder()
            .id(o.getId())
            .name(o.getName())
            .description(o.getDescription())
            .build())
        .toList();

    return Optional.of(Member.builder()
        .memberId(foundMemberEntity.getMemberId())
        .accountStatus(foundMemberEntity.getAccountStatus())
        .enrolledPrograms(enrolledPrograms)
        .firstName(foundMemberEntity.getGivenName())
        .lastName(foundMemberEntity.getSurname())
        .age(age)
        .address(foundMemberEntity.getAddress())
        .memberSince(foundMemberEntity.getEnrolledSince())
        .offerCategoryPreference(foundMemberEntity.getOfferCategoryPreference())
        .offers(memberOffers)
        .build());
  }

  public void enrollProgram(Long memberId, List<String> programId) {
    // check whether it is a valid member id
    memberRepository
        .findById(memberId)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format("Member with memberId=[%s] not found", memberId)));
    // check whether all program ids are valid
    List<ProgramEntity> programs = programRepository.findByProgramIdIn(programId);

    if (programs.size() != programId.size())
      throw new ResourceNotFoundException("Not all the program ids are valid.");

    List<MemberProgramEntity> memberProgramEntities =
        programs.stream()
            .map(
                eachProgram ->
                    MemberProgramEntity.builder()
                        .memberId(memberId)
                        .programId(eachProgram.getProgramId())
                        .build())
            .collect(Collectors.toList());

    // As the table is defined with member id + program id as the primary key, we don't need to
    // check if the enrollment is existing.
    // But if the db schema is changed, we need to think about the duplication.
    memberProgramRepository.saveAll(memberProgramEntities);
  }
}
