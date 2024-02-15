package com.dorjear.training.loyalty.member;

import static lombok.AccessLevel.PRIVATE;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;

@OpenAPIDefinition(
    info = @Info(
        title = "LSL Member Service",
        description = OpenApiDefinition.DESCRIPTION),
    tags = {
        @Tag(name = OpenApi.MEMBER_API_TAG),
        @Tag(name = OpenApi.PROGRAM_API_TAG)
    })
@NoArgsConstructor(access = PRIVATE)
public class OpenApiDefinition {

  static final String DESCRIPTION = """
      ## Student Coding Exercise
                
      This application is part of an exercises
       
      ### Overview
                
      The service provides APIs that allow:

      * creation and retrieval of member information
      * retrieval of member program information

      ### H2 Database
                
      To keep things simple, this student coding exercise uses an in-memory H2 database.
                
      We have made the H2 console available whilst the application is running at:
                
      * <a href='/h2-member-console' target='h2-member-console'>/h2-member-console</a>
      
      Connect to H2 using:
            
      * Driver Class:   **org.h2.Driver**
      * JDBC URL:       **jdbc:h2:mem:test**
      * User Name:      **guest**
      * Password:       **guest**

      """;
}
