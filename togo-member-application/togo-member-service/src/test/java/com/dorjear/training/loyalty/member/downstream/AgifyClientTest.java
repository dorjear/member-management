package com.dorjear.training.loyalty.member.downstream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

@Slf4j
public class AgifyClientTest {

    private ClientAndServer mockServer;

    @BeforeEach
    public void startServer() {
        mockServer = startClientAndServer();
    }

    @AfterEach
    public void stopServer() {
        mockServer.stop();
    }

    @Test
    public void testGetAgeByFirstName() {
        // Arrange
        String firstName = "John";
        int expectedAge = 30;
        int port = mockServer.getLocalPort();
        log.info("Port is " + port);
        mockServer.when(
                HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/").withQueryStringParameter("name", firstName)
        ).respond(
                HttpResponse.response().withStatusCode(200)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody("{\"name\":\"" + firstName + "\", \"age\":" + expectedAge + ", \"count\":100}")
        );

        // Adjust the AgifyClient to use the mock server URL
        RestTemplate restTemplate = new RestTemplate();
        AgifyClient agifyClient = new AgifyClient(restTemplate, "http://localhost:" + port + "/?name=");

        // Act
        int result = agifyClient.getAgeByFirstName(firstName);

        // Assert
        assertEquals(expectedAge, result, "The age should be 30 for John");
    }
}