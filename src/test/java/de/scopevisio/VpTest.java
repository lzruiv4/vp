package de.scopevisio;

import de.scopevisio.data.enums.CarType;
import de.scopevisio.data.enums.RegionType;
import de.scopevisio.data.model.Car;
import de.scopevisio.data.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VpTest {

    @LocalServerPort
    private int port;

    //    @Autowired
    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private String clientUrl;
    private String carUrl;
    private Client client;

    @BeforeEach
    void init() {
        String base = "http://localhost:";
        clientUrl = base + port + "/clients";
        carUrl = base + port + "/cars";
        client = new Client(
                null,
                "firstname",
                "lastname",
                "street",
                "123",
                "12589",
                "",
                new ArrayList<>());

        testRestTemplate.postForEntity(clientUrl + "/create", client, Client.class);
    }

//    @Test
//    void getClientByIdTest() {
//        ResponseEntity<Client> responseEntity = testRestTemplate.getForEntity(clientUrl + "/1", Client.class);
//        Client clientToBeCall = responseEntity.getBody();
//        assert clientToBeCall != null;
//        assertThat(clientToBeCall.getFirstname(), is("firstname"));
//        assertThat(clientToBeCall.getLastname(), is("lastname"));
//        assertThat(clientToBeCall.getCity(), is("Berlin"));
//    }

    @Test
    void getClientByInvalidId() {
        try {
            testRestTemplate.getForEntity(clientUrl + "/100", Client.class);
        } catch (IllegalArgumentException e) {
            assertEquals("Client not found", e.getMessage());
        }
    }

//    @Test
//    void getAllClientsTest() {
//        ResponseEntity<List<Client>> response = testRestTemplate.exchange(
//                "/clients/all",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {
//                }
//        );
//        assertThat(Objects.requireNonNull(response.getBody()).size(), is(1));
//    }

    @Test
    void createClientWithoutNameTest() {
        Client clientNr2 = new Client(
                null,
                null,
                null,
                "street2",
                "123",
                "99999",
                "",
                new ArrayList<>());
        var response = testRestTemplate.postForEntity(clientUrl + "/create", clientNr2, Client.class);
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    void updateClientTest() {
        ResponseEntity<Client> responseEntity = testRestTemplate.getForEntity(clientUrl + "/1", Client.class);
        Client clientToBeCall = responseEntity.getBody();
        assert clientToBeCall != null;
        assertEquals("Berlin", clientToBeCall.getCity());
        clientToBeCall.setFirstname("qwe");
        clientToBeCall.setLastname("asf");
        clientToBeCall.setStreet("zxxxx");
        clientToBeCall.setHouseNumber("98765");
        clientToBeCall.setPostCode("79194");
        testRestTemplate.put(clientUrl + "/update", clientToBeCall);

        ResponseEntity<Client> newClient = testRestTemplate.getForEntity(clientUrl + "/1", Client.class);
        Client result = Objects.requireNonNull(newClient.getBody());
        assertEquals("79194", result.getPostCode());
        assertEquals("Gundelfingen", result.getCity());
        assertEquals("qwe", result.getFirstname());
        assertEquals("asf", result.getLastname());
        assertEquals("zxxxx", result.getStreet());
        assertEquals("98765", result.getHouseNumber());
    }

    @Test
    void updateClientWithInvalidIdTest() {
        client.setClientId(100L);
        try {
            testRestTemplate.put(clientUrl + "/update", client);
        } catch (IllegalArgumentException e) {
            assertEquals("Client not found", e.getMessage());
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
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getCars().size(), is(1));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getCars().get(0).getRegionType(), is(RegionType.A));
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getCars().get(0).getVersicherungspraemie(),
                is(BigDecimal.valueOf(0.60).setScale(2, RoundingMode.HALF_UP)));
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
                BigDecimal.ONE,
                "30000");

        ResponseEntity<Car> carResponseEntity = testRestTemplate.postForEntity(carUrl + "/create?clientId=2", car2, Car.class);

        car2.setCarId(Objects.requireNonNull(carResponseEntity.getBody()).getCarId());
        car2.setCarType(CarType.PKW);
        car2.setMilesPerYear(BigDecimal.valueOf(12000));
        car2.setRegisteredPostalCode("50000");

        testRestTemplate.put(carUrl + "/update", car2);

        ResponseEntity<Client> newClient = testRestTemplate.getForEntity(clientUrl + "/2", Client.class);

        assertThat(Objects.requireNonNull(newClient.getBody()).getCars().get(0).getCarType(), is(CarType.PKW));
        assertThat(Objects.requireNonNull(newClient.getBody()).getCars().get(0).getRegionType(), is(RegionType.C));
        assertThat(Objects.requireNonNull(newClient.getBody()).getCars().get(0).getVersicherungspraemie(),
                is(BigDecimal.valueOf(2.40).setScale(2, RoundingMode.HALF_UP)));

        car2.setRegisteredPostalCode("70000");
        car2.setMilesPerYear(BigDecimal.valueOf(300000));
        testRestTemplate.put(carUrl + "/update", car2);
        newClient = testRestTemplate.getForEntity(clientUrl + "/2", Client.class);
        assertThat(Objects.requireNonNull(newClient.getBody()).getCars().get(0).getRegionType(), is(RegionType.D));

        car2.setRegisteredPostalCode("90000");
        testRestTemplate.put(carUrl + "/update", car2);
        newClient = testRestTemplate.getForEntity(clientUrl + "/2", Client.class);
        assertThat(Objects.requireNonNull(newClient.getBody()).getCars().get(0).getRegionType(), is(RegionType.E));

        car2.setMilesPerYear(BigDecimal.valueOf(-1));
        try {
            testRestTemplate.put(carUrl + "/update", car2);
        } catch (IllegalArgumentException e) {
            assertEquals("Something is going wrong, please check your input", e.getMessage());
        }
    }
}
