package com.dorjear.training.loyalty.member.repository;

import com.dorjear.training.loyalty.member.entity.MemberProgramEntity;
import com.dorjear.training.loyalty.member.entity.MemberProgramEntity.MemberProgramId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MemberProgramRepository extends CrudRepository<MemberProgramEntity, MemberProgramId> {

  @Query("SELECT mp FROM MemberProgramEntity mp WHERE mp.memberId = :memberId")
  Iterable<MemberProgramEntity> findAllByMemberId(@Param("memberId") final Long memberId);
}
