package Controller;

import Model.Match;
import Model.MatchManager;
import View.MatchView;

import java.time.LocalDate;
import java.util.List;

public class MatchController {

    private MatchView view;
    private boolean isAdministrator;
    private MatchManager matchManager;

    public MatchController(MatchView view, MatchManager matchManager) {
        this.view = view;
        this.matchManager = matchManager;
        this.isAdministrator = false;
    }

    public void start(){
        boolean running = true;
        //while (running) {
            view.displayMenu();
            int choice = view.getUserChoice();
            handleUserChoice(choice);
        //}
    }

    private void handleUserChoice(int userChoice) {
        switch (userChoice) {
            case 0 -> registerAsAdministrator();
            case 1 -> viewUpcomingMatches(); //View upcoming matches
            case 2 -> viewOldMatches(); //View Old Matches
            default -> view.showError("Invalid choice. Please try again.");
        }
    }

    public void viewUpcomingMatches() {
        LocalDate today = LocalDate.now();
        List<Match> upcomingMatches = matchManager.getMatchesAfter(today); // Filter matches
        if (upcomingMatches.isEmpty()) {
            view.showMessage("No upcoming matches found.");
        } else {
            view.displayMatches(upcomingMatches); // Display matches in the View
        }
    }

    public void viewOldMatches() {
        LocalDate today = LocalDate.now();
        List<Match> upcomingMatches = matchManager.getMatchesBefore(today); // Filter matches
        if (upcomingMatches.isEmpty()) {
            view.showMessage("No upcoming matches found.");
        } else {
            view.displayMatches(upcomingMatches); // Display matches in the View
        }
    }

    public void viewAllMatches() {
        view.displayMatches(matchManager.getListOfMatches());
    }

    public void registerAsAdministrator() {
        //TODO
    }



}
