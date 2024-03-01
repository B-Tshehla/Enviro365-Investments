package com.enviro.assessment.grad001.boitumelotshehla.Controller;

import com.enviro.assessment.grad001.boitumelotshehla.AbstractIntegrationTest;
import com.enviro.assessment.grad001.boitumelotshehla.dto.InvestorDto;
import com.enviro.assessment.grad001.boitumelotshehla.mapper.InvestorMapper;
import com.enviro.assessment.grad001.boitumelotshehla.model.Investor;
import com.enviro.assessment.grad001.boitumelotshehla.repository.InvestorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InvestorControllerTest extends AbstractIntegrationTest {

    @Autowired
    InvestorRepository investorRepository;

    @Autowired
    InvestorMapper investorMapper;

    @Test
    void createInvestorTest() {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setMiddleName("Tumi")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");


        InvestorDto response = webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(InvestorDto.class)
                .returnResult().getResponseBody();

        assertNotNull(response);
        assertEquals(response.getFirstName(), requestDto.getFirstName());
        assertEquals(response.getLastName(), requestDto.getLastName());
        assertEquals(response.getMiddleName(), requestDto.getMiddleName());
        assertEquals(response.getIdNumber(), requestDto.getIdNumber());
        assertEquals(response.getEmail(), requestDto.getEmail());
        assertEquals(response.getStreetNumber(), requestDto.getStreetNumber());
        assertEquals(response.getStreetName(), requestDto.getStreetName());
        assertEquals(response.getPostalCode(), requestDto.getPostalCode());
        assertEquals(response.getProvince(), requestDto.getProvince());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123", "!@#$%", "Boitumelo123"})
    void validateFirstNameWithBadData(String firstName) {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName(firstName)
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");

        webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123", "!@#$%", "Boitumelo123"})
    void validateMiddleNameWithBadData(String middleName) {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setMiddleName(middleName)
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");

        webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123", "!@#$%", "Tshehla123"})
    void validateLastNameWithBadData(String lastName) {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setLastName(lastName)
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");

        webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "123", "!@#$%", "Cornwell123"})
    void validateStreetNameWithBadData(String streetName) {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName(streetName)
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");

        webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123", "!@#$%", "Johannesburg123"})
    void validateCityNameWithBadData(String cityName) {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity(cityName)
                .setPostalCode("1575")
                .setProvince("Gauteng");

        webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123", "!@#$%", "Gauteng123"})
    void validateProvinceNameWithBadData(String provinceName) {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince(provinceName);

        webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "invalid.email", "noAtSign", "@missingLocalPart.com", "spaces @in@ between.com"})
    void validateEmailWithBadData(String email) {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail(email)
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");

        webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "12345678901234567890", "invalidCharacters!@#", "12345678901234567X", "20000101123456789"})
    void validateIdNumberWithBadData(String idNumber) {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber(idNumber)
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");

        webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "invalidStreet", "123abc", "!@#$%", "0"})
    void validateStreetNumberWithBadData(String streetNumber) {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber(streetNumber)
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");

        webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "invalidPostalCode", "12345", "!@#$%", "abc", "0000", "12345-6789"})
    void validatePostalCodeWithBadData(String postalCode) {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode(postalCode)
                .setProvince("Gauteng");

        webTestClient.post()
                .uri("/api/v1/investor")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void findInvestorByIdTest() {
        InvestorDto requestDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setMiddleName("Tumi")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");
        Investor investor = investorMapper.toEntity(requestDto);
        Investor savedInvestor = investorRepository.save(investor);

        InvestorDto response = webTestClient.get()
                .uri("api/v1/investor/{investor-id}", savedInvestor.getId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(InvestorDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(response);
        assertEquals(response.getFirstName(), requestDto.getFirstName());
        assertEquals(response.getLastName(), requestDto.getLastName());
        assertEquals(response.getMiddleName(), requestDto.getMiddleName());
        assertEquals(response.getIdNumber(), requestDto.getIdNumber());
        assertEquals(response.getEmail(), requestDto.getEmail());
        assertEquals(response.getStreetNumber(), requestDto.getStreetNumber());
        assertEquals(response.getStreetName(), requestDto.getStreetName());
        assertEquals(response.getPostalCode(), requestDto.getPostalCode());
        assertEquals(response.getProvince(), requestDto.getProvince());
    }

    @Test
    void findInvestorByInvalidIdTest() {
        Long id = 99L;
        webTestClient.get()
                .uri("api/v1/investor/{investor-id}", id)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void updateInvestorByIdTest() {
        InvestorDto saveInvestorDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setMiddleName("Tumi")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");
        Investor investor = investorMapper.toEntity(saveInvestorDto);
        Investor savedInvestor = investorRepository.save(investor);

        InvestorDto requestDto = new InvestorDto()
                .setCity("Pretoria");

        InvestorDto response = webTestClient.put()
                .uri("api/v1/investor/{investor-id}", savedInvestor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(InvestorDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(response);
        assertEquals(response.getFirstName(), saveInvestorDto.getFirstName());
        assertEquals(response.getLastName(), saveInvestorDto.getLastName());
        assertEquals(response.getMiddleName(), saveInvestorDto.getMiddleName());
        assertEquals(response.getIdNumber(), saveInvestorDto.getIdNumber());
        assertEquals(response.getEmail(), saveInvestorDto.getEmail());
        assertEquals(response.getStreetNumber(), saveInvestorDto.getStreetNumber());
        assertEquals(response.getStreetName(), saveInvestorDto.getStreetName());
        assertEquals(response.getCity(), requestDto.getCity());
        assertEquals(response.getPostalCode(), saveInvestorDto.getPostalCode());
        assertEquals(response.getProvince(), saveInvestorDto.getProvince());
    }

    @Test
    void updateInvestorByInvalidIdTest() {
        Long id = 99L;
        InvestorDto requestDto = new InvestorDto()
                .setCity("Pretoria");

        webTestClient.put()
                .uri("api/v1/investor/{investor-id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    void updateInvestorByIdValidationTest() {
        InvestorDto saveInvestorDto = new InvestorDto()
                .setFirstName("Boitumelo")
                .setMiddleName("Tumi")
                .setLastName("Tshehla")
                .setEmail("boitumelotshehla@gmail.com")
                .setIdNumber("9901215300524")
                .setStreetNumber("19935")
                .setStreetName("Cornwell")
                .setCity("Johannesburg")
                .setPostalCode("1575")
                .setProvince("Gauteng");
        Investor investor = investorMapper.toEntity(saveInvestorDto);
        Investor savedInvestor = investorRepository.save(investor);

        InvestorDto requestDto = new InvestorDto()
                .setIdNumber("99012153");

        webTestClient.put()
                .uri("api/v1/investor/{investor-id}", savedInvestor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}