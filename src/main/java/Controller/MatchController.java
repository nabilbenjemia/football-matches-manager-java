package Controller;

import Model.Competition;
import Model.Match;
import Model.MatchManager;
import View.MatchView;
import util.DatabaseUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static util.DatabaseUtil.getConnection;

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
            case 1 -> getMatchesAfter(LocalDate.now()); //View upcoming matches
            case 2 -> getMatchesBefore(LocalDate.now()); //View Old Matches
            case 3 -> viewAllMatches(); //view all Matches
            default -> view.showError("Invalid choice. Please try again.");
        }
    }

    public void getMatchesAfter(LocalDate date) {
        try {
            String query = "select * from matches where match_day > ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, date.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Match> matches = retrieveMatchData(resultSet);
            view.displayMatches(matches);
        } catch (SQLException e) {
            e.printStackTrace();
            view.showError("An error occurred while retrieving matches.");
        }
    }

    public void getMatchesBefore(LocalDate date) {
        try {
            String query = "select * from matches where match_day < ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, date.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Match> matches = retrieveMatchData(resultSet);
            view.displayMatches(matches);
        } catch (SQLException e) {
            e.printStackTrace();
            view.showError("An error occurred while retrieving matches.");
        }
    }

    private List<Match> retrieveMatchData(ResultSet resultSet) {
        List<Match> matches = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String opponent = resultSet.getString("opponent");
                String matchDay = resultSet.getString("match_day");
                int competition = resultSet.getInt("competition");
                boolean isHome = resultSet.getBoolean("is_home");
                boolean isFinished = resultSet.getBoolean("is_finished");
                int scoredGoals = resultSet.getInt("scored_goals");
                int opponentGoals = resultSet.getInt("opponent_goals");
                Match match = new Match(opponent, LocalDate.parse(matchDay), Competition.values()[competition], isHome);
                match.setFinished(isFinished);
                match.setScoredGoals(scoredGoals);
                match.setOpponentGoals(opponentGoals);
                matches.add(match);

            }
        } catch (SQLException e){
            e.printStackTrace();
            view.showError("An error occurred while retrieving all matches.");
        }
        return matches;

    }
    public void viewAllMatches() {
        try {
            Statement statement = DatabaseUtil.getConnection().createStatement();
            String query = "select * from matches";
            ResultSet resultSet = statement.executeQuery(query);
            List<Match> matches = retrieveMatchData(resultSet);
            view.displayMatches(matches);
        } catch (SQLException e) {
            e.printStackTrace();
            view.showError("An error occurred while retrieving all matches.");
        }
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
        String insertQuery = "insert into matches(opponent, match_day, competition, is_home) values (?, ?, ?, ?);";
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(insertQuery);
            prepareStatement.setString(1,view.getOpponent());
            prepareStatement.setString(2, view.getMatchDay());
            prepareStatement.setInt(3,view.getCompetition().ordinal());
            prepareStatement.setBoolean(4,view.isHome());
            int rowsInserted = prepareStatement.executeUpdate();
            if (rowsInserted > 0) {
                view.showMessage("Match added successfully!");
            } else {
                view.showError("Failed to add match.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            view.showError("An error occurred while adding the match.");
        }



    }

    private void changeMatchDay() {
        String[] input = view.getNewMatchDay();
        String updateQuery = "UPDATE matches SET match_day = ? WHERE match_day = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            //preparedStatement.setDate(1, java.sql.Date.valueOf(input[1]));
            //preparedStatement.setDate(2, java.sql.Date.valueOf(input[0]));

            preparedStatement.setString(1, input[1]);
            preparedStatement.setString(2, input[0]);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                view.showMessage("Match date updated successfully!");
            } else {
                view.showError("No match found with the given date.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showError("An error occurred while updating the match.");
        }
    }

}
