package Model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {
/*
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

    @Test
    public void testDeleteMatch() {
        MatchManager matchManager = new MatchManager();
        assertEquals(matchManager.getTotalNumberOfMatchs(), 0);
        matchManager.addMatch("ESS", LocalDate.parse("2022-09-08"), Competition.CUP, true);
        assertEquals(matchManager.getTotalNumberOfMatchs(), 1);
        matchManager.removeMatch(LocalDate.parse("2022-09-08"));
        assertEquals(matchManager.getTotalNumberOfMatchs(), 0);
    }

    @Test
    public void testDuplicateDate() {
        MatchManager matchManager = new MatchManager();
        matchManager.addMatch("ESS", LocalDate.parse("2022-09-08"), Competition.CUP, true);
        matchManager.addMatch("ASM", LocalDate.parse("2022-09-08"), Competition.FRIENDLY, false);

        Match actualMatch = matchManager.getMatch(LocalDate.parse("2022-09-08"));
        assertEquals(matchManager.getTotalNumberOfMatchs(), 1);
        assertEquals(actualMatch.getOpponent(), "ESS");
    }

    @Test
    public void testUpdateMatchDay() {
        MatchManager matchManager = new MatchManager();
        matchManager.addMatch("ESS", LocalDate.parse("2022-09-08"), Competition.CUP, true);
        matchManager.addMatch("ASM", LocalDate.parse("2020-12-17"), Competition.FRIENDLY, false);

        matchManager.updateMatchDay(LocalDate.parse("2022-09-08"), LocalDate.parse("2022-09-10"));
        Match actualMatch = matchManager.getMatch(LocalDate.parse("2022-09-10"));
        assertNotNull(actualMatch);
        assertEquals(actualMatch.getOpponent(), "ESS");
        assertEquals(actualMatch.getCompetition(), Competition.CUP);
        assertTrue(actualMatch.isHome());


    }
*/
}
