package Main;

import Controller.MatchController;
import Model.Competition;
import Model.Match;
import Model.MatchManager;
import View.MatchView;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

public class main {

    public static void main(String[] args) {
        MatchView view = new MatchView();
        MatchManager matchManager = new MatchManager();
        //Match match = new Match("AL Ahly", LocalDate.parse("2025-03-03"), Competition.CHAMPIONS_LEAGUE, true);
        //Match match2 = new Match("ASM", LocalDate.parse("2022-04-03"), Competition.FRIENDLY, true);

        //matchManager.addMatch(match);
        //matchManager.addMatch(match2);
        MatchController controller = new MatchController(matchManager);

    }
}
