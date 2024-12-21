package Controller;

import Model.Match;
import Model.MatchManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping("/matches")
public class MatchController {

    private MatchManager matchManager;

    @Autowired
    public MatchController(MatchManager matchManager) {
        this.matchManager = matchManager;
    }

    @GetMapping("/all")
    public List<Match> getAllMatches() {
        return matchManager.getAllMatches();
    }

    // View old matches (before today)
    @GetMapping("/old")
    public List<Match> getOldMatches() {
        return matchManager.getMatchesBefore(LocalDate.now());
    }

    // View upcoming matches (after today)
    @GetMapping("/upcoming")
    public List<Match> getUpcomingMatches() {
        return matchManager.getMatchesAfter(LocalDate.now());
    }



    // Add a new match
    @PostMapping
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
    @DeleteMapping("/{matchDay}")
    public ResponseEntity<String> deleteMatch(@PathVariable("matchDay") String matchDay) {
        boolean success = matchManager.removeMatch(LocalDate.parse(matchDay));
        if(success) {
            return ResponseEntity.ok("Match deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete match");
        }
    }

    // Update an existing match by matchDay
    @PutMapping("/{matchDay}")
    public ResponseEntity<String> updateMatch(@PathVariable("matchDay") String matchDay, @RequestBody Match updatedMatch) {
        Match match = matchManager.getMatch(LocalDate.parse(matchDay));
        if (match != null) {
            boolean success = matchManager.updateMatch(LocalDate.parse(matchDay), updatedMatch);
            if(success) {
                return ResponseEntity.ok("Match updated successfully!");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Match could not be updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found.");
        }

    }


}
