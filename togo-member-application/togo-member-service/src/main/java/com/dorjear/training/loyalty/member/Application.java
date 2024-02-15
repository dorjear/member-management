package com.dorjear.training.loyalty.member;

import com.dorjear.training.loyalty.member.client.OfferClient;
import io.swagger.v3.oas.annotations.Hidden;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients(basePackageClasses = OfferClient.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Hidden
  @GetMapping("/")
  public void home(final HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui/index.html?url=/v3/api-docs");
  }
}
