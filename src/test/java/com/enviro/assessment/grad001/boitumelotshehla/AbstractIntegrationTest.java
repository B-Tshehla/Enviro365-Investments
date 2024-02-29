package com.enviro.assessment.grad001.boitumelotshehla;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AbstractIntegrationTest {

    @Autowired
    protected WebTestClient webTestClient;
    protected static final GenericContainer<?> mailHog = new GenericContainer<>("mailhog/mailhog")
            .withExposedPorts(1025);

    static {
        mailHog.start();
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.mail.host", mailHog::getHost);
        registry.add("spring.mail.port", mailHog::getFirstMappedPort);
        registry.add("spring.mail.username", () -> "admin@enfint.tech");
        registry.add("spring.mail.password", () -> "demo");
        registry.add("spring.mail.properties.mail.smtp.auth", () -> false);
        registry.add("spring.mail.properties.mail.smtp.starttls.enable", () -> false);
    }
}
