package de.scopevisio.vp.backend.controller;

import de.scopevisio.vp.backend.data.enums.CarType;
import de.scopevisio.vp.backend.data.enums.RegionType;
import de.scopevisio.vp.backend.data.model.Car;
import de.scopevisio.vp.backend.data.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VPControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String base = "http://localhost:";
    private String clientUrl;
    private String carUrl;
    private Client client;

    @BeforeEach
    void init() {
        clientUrl = base + port + "/clients";
        carUrl = base + port + "/cars";
        client = new Client(
                1L,
                "firstname",
                "lastname",
                "street",
                "123",
                "12589",
                "",
                new ArrayList<>());
        testRestTemplate.postForEntity(clientUrl + "/create", client, Client.class);
    }

    @Test
    void getClientByIdTest() {
        ResponseEntity<Client> responseEntity = testRestTemplate.getForEntity(clientUrl + "/1", Client.class);
        Client clientToBeCall = responseEntity.getBody();
        assert clientToBeCall != null;
        assertThat(clientToBeCall.getFirstname()).isEqualTo("firstname");
        assertThat(clientToBeCall.getLastname()).isEqualTo("lastname");
        assertThat(clientToBeCall.getCity()).isEqualTo("Berlin");
    }

    @Test
    void getClientByInvalidId() {
        try {
            testRestTemplate.getForEntity(clientUrl + "/100", Client.class);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Client not found");
        }
    }

    @Test
    void getAllClientsTest() {
        ResponseEntity<List<Client>> response = testRestTemplate.exchange(
                "/clients/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Client>>() {}
        );
        assertThat(Objects.requireNonNull(response.getBody()).size()).isEqualTo(1);
    }

    @Test
    void createClientWithoutNameTest() {
        try {
            Client clientNr2 = new Client(
                    null,
                    null,
                    null,
                    "street2",
                    "123",
                    "99999",
                    "",
                    new ArrayList<>());
            testRestTemplate.postForEntity(clientUrl + "/create", clientNr2, Client.class);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void updateClientTest() {
        ResponseEntity<Client> responseEntity = testRestTemplate.getForEntity(clientUrl + "/1", Client.class);
        Client clientToBeCall = responseEntity.getBody();
        assert clientToBeCall != null;
        assertEquals(clientToBeCall.getCity(), "Berlin");
        clientToBeCall.setFirstname("qwe");
        clientToBeCall.setLastname("asf");
        clientToBeCall.setStreet("zxxxx");
        clientToBeCall.setHouseNumber("98765");
        clientToBeCall.setPostCode("79194");
        testRestTemplate.put(clientUrl + "/update", clientToBeCall);

        ResponseEntity<Client> newClient = testRestTemplate.getForEntity(clientUrl + "/1", Client.class);
        Client result = Objects.requireNonNull(newClient.getBody());
        assertEquals(result.getPostCode(), "79194");
        assertEquals(result.getCity(), "Gundelfingen");
        assertEquals(result.getFirstname(), "qwe");
        assertEquals(result.getLastname(), "asf");
        assertEquals(result.getStreet(), "zxxxx");
        assertEquals(result.getHouseNumber(), "98765");
    }

    @Test
    void updateClientWithInvalidIdTest() {
        client.setClientId(100L);
        try {
            testRestTemplate.put(clientUrl + "/update", client);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Client not found");
        }
    }

    @Test
    void addACarTest() {
        Car car1 = new Car(
                null,
                CarType.PKW,
                BigDecimal.TEN,
                null,
                null,
                "10000");

        testRestTemplate.postForEntity(carUrl + "/create?clientId=1", car1, Car.class);

        ResponseEntity<Client> responseEntity = testRestTemplate.getForEntity(clientUrl + "/1", Client.class);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getCars().size()).isEqualTo(1);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getCars().get(0).getRegionType()).isEqualTo(RegionType.A);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getCars().get(0).getVersicherungspraemie())
                .isEqualTo(BigDecimal.valueOf(0.60).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void updateACarTest() {
        Client clientNr2 = new Client(
                2L,
                "kk",
                "jj",
                "street2",
                "123",
                "99999",
                "",
                new ArrayList<>());
        testRestTemplate.postForEntity(clientUrl + "/create", clientNr2, Client.class);

        Car car2 = new Car(
                null,
                CarType.LKW,
                BigDecimal.valueOf(6000),
                null,
                null,
                "30000");

        ResponseEntity<Car> carResponseEntity = testRestTemplate.postForEntity(carUrl + "/create?clientId=2", car2, Car.class);

        car2.setCarId(Objects.requireNonNull(carResponseEntity.getBody()).getCarId());
        car2.setCarType(CarType.PKW);
        car2.setMilesPerYear(BigDecimal.valueOf(12000));
        car2.setRegisteredPostalCode("50000");

        testRestTemplate.put(carUrl + "/update", car2);

        ResponseEntity<Client> newClient = testRestTemplate.getForEntity(clientUrl + "/2", Client.class);

        assertThat(Objects.requireNonNull(newClient.getBody()).getCars().get(0).getCarType()).isEqualTo(CarType.PKW);
        assertThat(Objects.requireNonNull(newClient.getBody()).getCars().get(0).getRegionType()).isEqualTo(RegionType.C);
        assertThat(Objects.requireNonNull(newClient.getBody()).getCars().get(0).getVersicherungspraemie())
                .isEqualTo(BigDecimal.valueOf(2.40).setScale(2, RoundingMode.HALF_UP));

        car2.setRegisteredPostalCode("70000");
        car2.setMilesPerYear(BigDecimal.valueOf(300000));
        testRestTemplate.put(carUrl + "/update", car2);
        newClient = testRestTemplate.getForEntity(clientUrl + "/2", Client.class);
        assertThat(Objects.requireNonNull(newClient.getBody()).getCars().get(0).getRegionType()).isEqualTo(RegionType.D);

        car2.setRegisteredPostalCode("90000");
        testRestTemplate.put(carUrl + "/update", car2);
        newClient = testRestTemplate.getForEntity(clientUrl + "/2", Client.class);
        assertThat(Objects.requireNonNull(newClient.getBody()).getCars().get(0).getRegionType()).isEqualTo(RegionType.E);

        car2.setMilesPerYear(BigDecimal.valueOf(-1));
        try{
            testRestTemplate.put(carUrl + "/update", car2);
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Something is going wrong, please check your input");
        }
    }
}
