package Controller;

import Model.Match;
import Model.MatchManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping("/matches")
@CrossOrigin(origins = "http://localhost:64127")
public class MatchController {

    private MatchManager matchManager;

    @Autowired
    public MatchController(MatchManager matchManager) {
        this.matchManager = matchManager;
    }

    //@GetMapping("/all")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Match> getAllMatches() {
        return matchManager.getAllMatches();
    }

    // View old matches (before today)
    //@GetMapping("/old")
    @RequestMapping(value = "/old", method = RequestMethod.GET)
    public List<Match> getOldMatches() {
        return matchManager.getMatchesBefore(LocalDate.now());
    }

    // View upcoming matches (after today)
    //@GetMapping("/upcoming")
    @RequestMapping(value = "/upcoming", method = RequestMethod.GET)
    public List<Match> getUpcomingMatches() {
        return matchManager.getMatchesAfter(LocalDate.now());
    }



    // Add a new match
    //@PostMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addMatch(@RequestBody String matchJSON) {


        try {
            Match match = new Match(matchJSON);

        boolean success = matchManager.addMatch(match);
        if(success) {
            return ResponseEntity.ok("Match added successfully!");
        }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add a match");

        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format");

        }
    }

    // Delete a match by matchDay
    //@DeleteMapping("/{matchDay}")
    @RequestMapping(value = "/{matchDay}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMatch(@PathVariable("matchDay") String matchDay) {
        boolean success = matchManager.removeMatch(LocalDate.parse(matchDay));
        if(success) {
            return ResponseEntity.ok("Match deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete match");
        }
    }

    // Update an existing match by matchDay
    //todo: update view part also
    //@PutMapping("/{matchDay}")
    @RequestMapping(value = "/{matchDay}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateMatch(@PathVariable("matchDay") String matchDay, @RequestBody String updatedMatchJSON) {

        Match match = matchManager.getMatch(LocalDate.parse(matchDay));
        try {
        if (match != null) {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(updatedMatchJSON);

            if (rootNode.has("opponent")) {
                matchManager.setOpponent(matchDay, rootNode.get("opponent").asText());
            } else {
                System.out.println("NO OPPONENT FOUND");
            }

            if (rootNode.has("competition")) {
                int competitionValue = rootNode.get("competition").asInt();
                matchManager.setCompetition(matchDay, competitionValue); // assuming competition is an enum
            }

            // Check if the 'home' field exists and update it
            if (rootNode.has("home")) {
                matchManager.setIsHome(matchDay, rootNode.get("home").asInt());
            }

            if (rootNode.has("finished")) {
                matchManager.setIsFinished(matchDay, rootNode.get("finished").asInt());
            }

            if (rootNode.has("scoredGoals")) {
                matchManager.setScoredGoals(matchDay, rootNode.get("scoredGoals").asInt());
            }

            if (rootNode.has("opponentGoals")) {
                matchManager.setOpponentGoals(matchDay, rootNode.get("opponentGoals").asInt());
            }
            //last thing to be updated
            if (rootNode.has("date")) {
                matchManager.setMatchDay(matchDay, rootNode.get("date").asText());
            }


            return ResponseEntity.ok("Match updated successfully");


        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found.");
        }
    } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("updated Match not found.");
    }
    }


}
