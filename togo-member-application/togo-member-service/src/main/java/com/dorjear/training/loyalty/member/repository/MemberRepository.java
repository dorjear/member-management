package com.dorjear.training.loyalty.member.repository;

import com.dorjear.training.loyalty.member.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberEntity, Long> {

}
