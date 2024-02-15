package com.dorjear.training.loyalty.offers;

import static lombok.AccessLevel.PRIVATE;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;

@OpenAPIDefinition(
    info = @Info(
        title = "LSL Offers Service",
        description = OpenApiDefinition.DESCRIPTION),
    tags = {
        @Tag(name = OpenApi.OFFERS_API_TAG)
    })
@NoArgsConstructor(access = PRIVATE)
public class OpenApiDefinition {

  static final String DESCRIPTION = """
      ## Student Coding Exercise
                
      This application is part of an exercise
       
      ### Overview
                
      The service provides APIs that allow:

      * retrieval of offers

      ### H2 Database
                
      To keep things simple, this student coding exercise uses an in-memory H2 database.
                
      We have made the H2 console available whilst the application is running at:
                
      * <a href='/h2-offers-console' target='h2-offers-console'>/h2-offers-console</a>
            
      Connect to H2 using:
            
      * Driver Class:   **org.h2.Driver**
      * JDBC URL:       **jdbc:h2:mem:test**
      * User Name:      **guest**
      * Password:       **guest**

      """;
}
