package com.dorjear.training.loyalty.api;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiResponses({
    @ApiResponse(
        responseCode = "400",
        description = "Bad Request. Please check your request and ensure that all request data is valid.",
        content = @Content),
    @ApiResponse(
        responseCode = "404",
        description = "Not Found.  The requested resource could not be found.",
        content = @Content),
    @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error. Something didn't work at our end.",
        content = @Content)
})
@Target({METHOD, TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DefaultApiErrorResponses {

}