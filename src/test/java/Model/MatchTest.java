package Model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchTest {

    @Test
    public void testMatchManager() {

        MatchManager matchManager = new MatchManager();
        matchManager.addMatch("ESS", LocalDate.parse("2022-09-08"), Competition.CUP, true);
        matchManager.setMatchFinished(LocalDate.parse("2022-09-08"));
        matchManager.setOpponentGoals(LocalDate.parse("2022-09-08"), 2);
        matchManager.setScoredGoals(LocalDate.parse("2022-09-08"), 5);

        Match match = matchManager.getMatch(LocalDate.parse("2022-09-08"));
        assertEquals("2022-09-08", match.getMatchDay().toString());
        assertEquals("ESS", match.getOpponent());
        assertTrue(match.isHome());
    }
}
