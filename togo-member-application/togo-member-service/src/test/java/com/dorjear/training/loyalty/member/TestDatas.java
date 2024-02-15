package com.dorjear.training.loyalty.member;

import com.dorjear.training.loyalty.member.entity.MemberEntity;
import com.dorjear.training.loyalty.member.entity.MemberProgramEntity;
import com.dorjear.training.loyalty.member.entity.ProgramEntity;
import com.dorjear.training.loyalty.model.AccountStatus;
import com.dorjear.training.loyalty.model.OfferCategory;
import com.dorjear.training.loyalty.member.model.Address;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

public class TestDatas {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static final Long MEMBER_ID = 1L;

    public static final List<String> PROGRAM_ID = List.of("FF", "ER");

    public static final List<String> PROGRAM_IDS = List.of("FF", "ER");

    public static final MemberProgramEntity MEMBER_PROGRAM_ENTITY_0 = MemberProgramEntity.builder()
            .memberId(MEMBER_ID)
            .programId(PROGRAM_IDS.get(0))
            .build();

    public static final MemberProgramEntity MEMBER_PROGRAM_ENTITY_1 = MemberProgramEntity.builder()
            .memberId(MEMBER_ID)
            .programId(PROGRAM_IDS.get(1))
            .build();

    public static final ProgramEntity PROGRAM_ENTITY_0 = ProgramEntity.builder()
            .programId(PROGRAM_IDS.get(0))
            .programDescription("Frequent Flyer program")
            .build();

    public static final ProgramEntity PROGRAM_ENTITY_1 = ProgramEntity.builder()
            .programId(PROGRAM_IDS.get(0))
            .programDescription("Frequent Flyer program")
            .build();

    public static Address address = Address
            .builder()
            .line1("Unit 2")
            .line2("10 Bourke road")
            .city("Mascot")
            .state("NSW")
            .postcode("2020")
            .country("Australia")
            .build();

    public static final MemberEntity MEMBER_ENTITY_0 = MemberEntity.builder()
            .memberId(MEMBER_ID)
            .accountStatus(AccountStatus.ACTIVE)
            .givenName("Fred")
            .surname("Flintstone")
            .address(address)
            .enrolledSince(LocalDate.of(2017, 8, 12))
            .offerCategoryPreference(OfferCategory.NATURE)
            .build();
    public static final List<MemberProgramEntity> MEMBER_PROGRAM_ENTITIES_2 = List.of(MEMBER_PROGRAM_ENTITY_0, MEMBER_PROGRAM_ENTITY_1);
    public static final List<ProgramEntity> PROGRAMS_ENTITIES_1 = List.of(PROGRAM_ENTITY_0);
    public static final List<ProgramEntity> PROGRAMS_ENTITIES_2 = List.of(PROGRAM_ENTITY_0, PROGRAM_ENTITY_1);


}
