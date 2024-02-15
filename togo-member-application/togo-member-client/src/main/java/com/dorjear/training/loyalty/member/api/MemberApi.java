package com.dorjear.training.loyalty.member.api;

import com.dorjear.training.loyalty.api.DefaultApiErrorResponses;
import com.dorjear.training.loyalty.member.OpenApi;
import com.dorjear.training.loyalty.member.model.Member;
import com.dorjear.training.loyalty.member.model.MemberProgramEnrollmentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = OpenApi.MEMBER_API_TAG)
@DefaultApiErrorResponses
public interface MemberApi {

  @PostMapping(value = OpenApi.MEMBER_URL)
  @Operation(summary = "Create a member",
      description = "This API returns the newly created member's profile details such as name, statuses and enrolled programs.")
  @ApiResponse(
      responseCode = "200",
      description = "The response payload contains the member's profile details.",
      content = @Content(schema = @Schema(implementation = Member.class)))
  Member createMember(@RequestBody final Member member);

  @GetMapping(value = OpenApi.MEMBER_MEMBER_ID_URL)
  @Operation(summary = "Retrieve the member profile details by member-id",
      description = "This API returns the member profile details such as name, statuses and enrolled programs.")
  @ApiResponse(
      responseCode = "200",
      description = "The response payload contains the member's profile details.",
      content = @Content(schema = @Schema(implementation = Member.class)))
  Member getMember(
      @PathVariable
      @Parameter(name = "memberId", example = "110021") final Long memberId);

  @PostMapping(value = OpenApi.MEMBER_PROGRAMS_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Enroll a member into a program",
          description = "This API enroll a member into one or more programs.")
  @ApiResponse(
          responseCode = "200",
          description = "Response will be empty if enrollment is successful. Returning a member with latest position could be an option but considering the KISS principle just return empty until further requirement raised."
  )
  void enrollProgram(
      @PathVariable @Parameter(name = "memberId", example = "110021") final Long memberId,
      @RequestBody MemberProgramEnrollmentRequest memberProgramEnrollmentRequest);
}
