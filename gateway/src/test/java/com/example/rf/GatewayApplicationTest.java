package com.example.rf;

import com.netflix.zuul.context.RequestContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GatewayApplication.class)
public class GatewayApplicationTest {

    private static final String EXPECTED_JSON = "[{\"id\":3,\"tile\":\"Mr\",\"firstName\":\"Ritchie\",\"lastName\":\"Francis\",\"dob\":\"6/7/1974\",\"jobTitle\":\"Developer\",\"createDate\":\"2021-09-21T21:46:46\"}]";

    @Autowired
    private TestRestTemplate rest;

    static ConfigurableApplicationContext service;

    @BeforeAll
    public static void startBookService() {
        service = SpringApplication.run(BookService.class,
                "--server.port=8090");
    }

    @AfterAll
    public static void closeBookService() {
        service.close();
    }

    @BeforeEach
    public void setup() {
        RequestContext.testSetCurrentContext(new RequestContext());
    }

    @Test
    public void test() {
        String resp = rest.getForObject("/user-service/first-name/Ritchie", String.class);
        assertThat(resp).isEqualTo(EXPECTED_JSON);
    }

    @Configuration
    @EnableAutoConfiguration
    @RestController()
    @RequestMapping( "/users")
    static class BookService {
        @RequestMapping("/first-name/{firstName}")
        public String getAvailable(@PathVariable String firstName) {
            return EXPECTED_JSON;
        }
    }
}
