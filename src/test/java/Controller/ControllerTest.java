package Controller;

import Controller.MatchController;
import Model.Competition;
import Model.Match;
import Model.MatchManager;
import View.MatchView;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetAllMatches() {
        //GET request
        ResponseEntity<String> response = restTemplate.getForEntity("/matches/all", String.class);
        assertNotNull(response.getBody());
        System.out.println(response.getBody().replace("}", "}\n"));
        assertTrue(response.getStatusCode().is2xxSuccessful());


    }

    @Test
    void testGetOldMatches() {
        //GET request
        ResponseEntity<String> response = restTemplate.getForEntity("/matches/old", String.class);
        assertNotNull(response.getBody());
        System.out.println(response.getBody().replace("}", "}\n"));
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void testGetUpcomingMatches() {
        //GET request
        ResponseEntity<String> response = restTemplate.getForEntity("/matches/upcoming", String.class);
        assertNotNull(response.getBody());
        System.out.println(response.getBody().replace("}", "}\n"));
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void testAddMatch() {

        String newMatchJson = "{"
                + "\"opponent\": \"test Opponent\","
                + "\"date\": \"2024-12-21\","
                + "\"competition\": \"1\","
                + "\"home\": true"
                + "}";

        // Send POST request to add a match
        ResponseEntity<String> response = restTemplate.postForEntity("/matches", newMatchJson, String.class);

        // Print the response body, formatting it to insert a newline after each closing curly brace
        System.out.println(response.getBody().replace("}", "}\n"));

        // Assert that the response contains the expected success message
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Match added successfully!", response.getBody());
    }

    @Test
    void testDeleteMatch() {
        String newMatchJson = "{"
                + "\"opponent\": \"test Delete Opponent\","
                + "\"date\": \"2080-01-01\","
                + "\"competition\": \"3\","
                + "\"home\": false"
                + "}";

        ResponseEntity<String> response = restTemplate.postForEntity("/matches", newMatchJson, String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals("Match added successfully!", response.getBody(), "Match was not added");

        restTemplate.delete("/matches/{matchDay}", "2080-01-01");
        ResponseEntity<String> deleteResponse = restTemplate.getForEntity("/matches/all", String.class);

        assertFalse(deleteResponse.getBody().contains("2080-01-01"), "Match was not removed");

    }

    @Test
    void testUpdateMatch() {
        String matchDay = "2028-06-12"; // Example match day
        String updatedMatchJSON = """
            {
                "opponent": "Team B"
            }
        """;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(updatedMatchJSON, headers);

        // Act
        ResponseEntity<String> response = restTemplate.exchange(
                "/matches/" + matchDay,
                HttpMethod.PUT,
                request,
                String.class
        );

        // Assert
        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());
        assert(response.getStatusCode() == HttpStatus.OK);

        ResponseEntity<String> updateResponse = restTemplate.getForEntity("/matches/all", String.class);
        System.out.println(updateResponse.getBody().replace("}", "}\n"));
        assertTrue(updateResponse.getBody().contains("Team B"), "Match was not updated");
        assertFalse(updateResponse.getBody().contains("Flamengo"), "Match was not updated");

    }

    //TODO: add duplicate matches
}
