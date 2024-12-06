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

    private static final String ADMIN_NAME = "admin123";
    private static final String ADMIN_PASSWORD = "ilovefootball";

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
            case 3 -> viewAllMatches(); //view all Matches
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
            view.showMessage("No old matches found.");
        } else {
            view.displayMatches(upcomingMatches); // Display matches in the View
        }
    }

    public void viewAllMatches() {
        view.displayMatches(matchManager.getListOfMatches());
    }

    public void registerAsAdministrator() {
        String[] credentials = view.getAdministratorCredentials();
        isAdministrator = verifyAdministrator(credentials[0], credentials[1]);
        if (isAdministrator) {
            view.showMessage("Welcome " + credentials[0]);
            handleAdminRequest();
        } else {
            view.showError("Please try again! Username or Password are wrong");
            view.getUserChoice();
        }
    }

    public boolean verifyAdministrator(String name, String password) {
        return name.equalsIgnoreCase(ADMIN_NAME) && password.equals(ADMIN_PASSWORD);
    }

    private void handleAdminRequest() {
        int adminChoice = view.displayAdminChoice();
        switch (adminChoice) {
            case 0 -> addNewMatch();
            //case 1 ->;
            case 2 -> changeMatchDay();
            //case 3 ->;
            default -> {
                view.showMessage("Wrong input");
                view.displayAdminChoice();
            }
        }
        viewAllMatches();
    }

    //add case of failing parsing
    private void addNewMatch() {
        Match match = new Match(view.getOpponent(), LocalDate.parse(view.getMatchDay()), view.getCompetition(), view.isHome());
        matchManager.addMatch(match);

    }

    private void changeMatchDay() {
        String[] input = view.getNewMatchDay();
        matchManager.getMatch(LocalDate.parse(input[0])).setMatchDay(LocalDate.parse(input[1]));
    }

}
