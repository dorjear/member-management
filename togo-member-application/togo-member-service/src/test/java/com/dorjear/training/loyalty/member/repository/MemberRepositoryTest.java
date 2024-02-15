package com.dorjear.training.loyalty.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.dorjear.training.loyalty.member.TestDatas;
import com.dorjear.training.loyalty.member.entity.MemberEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryTest {

  @Autowired private MemberRepository repository;

  @Test
  void shouldFindAllOffers() {
    MemberEntity savedMember = repository.save(TestDatas.MEMBER_ENTITY_0);
    List<MemberEntity> members =
        StreamSupport.stream(repository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    assertThat(members.size()).isEqualTo(6);

    MemberEntity member = repository.findById(savedMember.getMemberId()).get();
    assertThat(member).isEqualTo(savedMember);
  }
}
