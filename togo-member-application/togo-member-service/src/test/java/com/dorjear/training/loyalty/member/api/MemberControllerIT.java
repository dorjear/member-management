package com.dorjear.training.loyalty.member.api;

import static com.dorjear.training.loyalty.member.TestDatas.MEMBER_ID;
import static com.dorjear.training.loyalty.member.TestDatas.PROGRAM_ID;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dorjear.training.loyalty.ApplicationIT;
import com.dorjear.training.loyalty.api.InternalServerException;
import com.dorjear.training.loyalty.api.ResourceNotFoundException;
import com.dorjear.training.loyalty.member.service.MemberService;
import com.dorjear.training.loyalty.member.OpenApi;
import com.dorjear.training.loyalty.member.model.Member;
import com.dorjear.training.loyalty.member.model.MemberProgramEnrollmentRequest;
import com.dorjear.training.loyalty.member.model.Program;
import com.dorjear.training.loyalty.model.AccountStatus;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberControllerIT extends ApplicationIT {
  //No need to create a AbstractControllerIT.

  MockMvc mockMvc;

  @MockBean
  MemberService memberService;

  @Autowired
  WebApplicationContext wac;

  @BeforeEach
  void beforeEach() {

    this.mockMvc = MockMvcBuilders
        .webAppContextSetup(this.wac)
        .build();
  }

  @Test
  void getMember_shouldReturnHttpStatus200() throws Exception {

    final Member expected = Member.builder()
        .memberId(MEMBER_ID)
        .accountStatus(AccountStatus.ACTIVE)
        .firstName("Fred")
        .lastName("Flintstone")
        .enrolledPrograms(Set.of(Program.builder()
                .programId("FF")
                .marketingName("Frequent Flyer")
                .summaryDescription("Frequent Flyer Program")
            .build()))
        .build();

    when(memberService.getMember(MEMBER_ID)).thenReturn(Optional.of(expected));

    mockMvc.perform(
            get(OpenApi.MEMBER_MEMBER_ID_URL, MEMBER_ID)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.memberId", equalTo(MEMBER_ID.intValue())))
        .andExpect(jsonPath("$.accountStatus", is("ACTIVE")))
        .andExpect(jsonPath("$.firstName", is("Fred")))
        .andExpect(jsonPath("$.lastName", is("Flintstone")))
        .andExpect(jsonPath("$.enrolledPrograms[0].programId", is("FF")))
        .andExpect(jsonPath("$.enrolledPrograms[0].marketingName", is("Frequent Flyer")));
  }

  @Test
  void getMember_shouldReturnHttpStatus404() throws Exception {

    final ResourceNotFoundException exception = new ResourceNotFoundException("Member not found");

    when(memberService.getMember(MEMBER_ID)).thenThrow(exception);

    mockMvc.perform(
            get(OpenApi.MEMBER_MEMBER_ID_URL, MEMBER_ID)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  void getMember_shouldReturnHttpStatus500() throws Exception {

    final InternalServerException exception = new InternalServerException("Internal System Error");

    when(memberService.getMember(MEMBER_ID)).thenThrow(exception);

    mockMvc.perform(
            get(OpenApi.MEMBER_MEMBER_ID_URL, MEMBER_ID)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isInternalServerError());
  }

  @Test
  void enrollProgram_shouldReturnHttpStatus200() throws Exception {

    final MemberProgramEnrollmentRequest requestObject = MemberProgramEnrollmentRequest.builder()
        .programIds(PROGRAM_ID)
        .build();

    final String requestObjStr = objectMapper.writeValueAsString(requestObject);

    doNothing().when(memberService).enrollProgram(MEMBER_ID, PROGRAM_ID);

    mockMvc.perform(
            post(OpenApi.MEMBER_PROGRAMS_URL, MEMBER_ID)
                .content(requestObjStr)
                .characterEncoding(StandardCharsets.UTF_8.displayName())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

}
