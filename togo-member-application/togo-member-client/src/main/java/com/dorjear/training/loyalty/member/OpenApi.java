package com.dorjear.training.loyalty.member;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class OpenApi {

  public static final String MEMBER_API_TAG = "Member APIs";
  public static final String PROGRAM_API_TAG = "Program APIs";
  public static final String MEMBER_URL = "/member";
  public static final String MEMBER_MEMBER_ID_URL = "/member/{memberId}";
  public static final String MEMBER_PROGRAMS_URL = "/member/{memberId}/enrollment";

  public static final String PROGRAM_URL = "/program";
  public static final String PROGRAM_PROGRAM_ID_URL = "/program/{programId}";

}
