package Model;

import Controller.MatchController;
import View.MatchView;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

public class ControllerTest {

    @Test
    public void controllerSimpleTest() {
        MatchView view = new MatchView();
        MatchManager matchManager = new MatchManager();
        Match match = new Match("AL Ahly", LocalDate.parse("2025-03-03"), Competition.CHAMPIONS_LEAGUE, true);
        matchManager.addMatch(match);
        MatchController controller = new MatchController(view, matchManager);
        String simulatedInput = "1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream); // Set the custom InputStream

        controller.start();
        System.setIn(System.in);
        //missing assertions
    }
}
