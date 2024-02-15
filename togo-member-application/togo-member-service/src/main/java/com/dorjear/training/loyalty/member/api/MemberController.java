package com.dorjear.training.loyalty.member.api;

import com.dorjear.training.loyalty.api.ResourceNotFoundException;
import com.dorjear.training.loyalty.member.service.MemberService;
import com.dorjear.training.loyalty.member.model.Member;
import com.dorjear.training.loyalty.member.model.MemberProgramEnrollmentRequest;

import java.util.Optional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController implements MemberApi {

  private final MemberService memberService;

  @Autowired
  MemberController(final MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public Member createMember(final Member member) {
    return memberService.createMember(member);
  }

  @Override
  public Member getMember(final Long memberId) {

    final Optional<Member> foundMember = memberService.getMember(memberId);

    if (foundMember.isEmpty()) {
      throw new ResourceNotFoundException("No member exists with memberId=" + memberId);
    }

    return foundMember.get();
  }

  @Override
  @ResponseStatus(HttpStatus.OK)
  public void enrollProgram(@NotNull Long memberId, @NotNull MemberProgramEnrollmentRequest request) {
    memberService.enrollProgram(memberId, request.getProgramIds());
  }

}
