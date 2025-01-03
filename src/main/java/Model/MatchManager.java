package Model;

import org.springframework.stereotype.Service;
import util.DatabaseUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static util.DatabaseUtil.getConnection;

@Service
public class MatchManager {


    private Connection connection;

    public MatchManager() {
        try {
            this.connection = getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addMatch(String opponent, LocalDate matchDay, Competition competition, boolean isHome) {
        Match match = new Match(opponent, matchDay, competition, isHome);
        return addMatch(match);
    }

    public boolean addMatch(Match match) {
        try{
            Connection connection = DatabaseUtil.getConnection();

            String insertQuery = "INSERT INTO matches(opponent, match_day, competition, is_home) VALUES (?, ?, ?, ?)";
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, match.getOpponent());
            preparedStatement.setString(2, match.getMatchDay().toString());
            preparedStatement.setInt(3, match.getCompetition().ordinal());
            preparedStatement.setInt(4, match.isHome()? 1:0);

            //preparedStatement.setBoolean(4, match.isHome());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("problem when adding a match: " + e.getMessage());
            e.printStackTrace();
            return false;
            // Handle error
        }
    }

    public boolean removeMatch(LocalDate matchDay) {
        String deleteQuery = "DELETE FROM matches WHERE match_day = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, matchDay.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            // Handle error
        }
    }

    public Match getMatch(LocalDate matchDay) {
        String selectQuery = "SELECT * FROM matches WHERE match_day = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            // Set the matchDay in the prepared statement
            preparedStatement.setDate(1, Date.valueOf(matchDay));

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result
            if (resultSet.next()) {
                // Create a match object based on the result
                String opponent = resultSet.getString("opponent");
                LocalDate retrievedMatchDay = resultSet.getDate("match_day").toLocalDate();
                int competitionOrdinal = resultSet.getInt("competition");
                boolean isHome = resultSet.getBoolean("is_home");

                // Assuming the competition is an enum, convert the ordinal back
                Competition competition = Competition.values()[competitionOrdinal];

                // Create and return the Match object
                return new Match(opponent, retrievedMatchDay, competition, isHome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
        return null;
    }

    public boolean updateMatch(LocalDate matchDay, Match match) {
        String updateQuery = "UPDATE matches SET opponent = ?, competition = ?, is_home = ?, is_finished = ?, scored_goals = ?, opponent_goals = ? WHERE match_day = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, match.getOpponent());
            preparedStatement.setInt(2, match.getCompetition().ordinal());
            preparedStatement.setBoolean(3, match.isHome());
            preparedStatement.setBoolean(4, match.isFinished());
            preparedStatement.setInt(5, match.getScoredGoals());
            preparedStatement.setInt(6, match.getOpponentGoals());
            preparedStatement.setString(7, matchDay.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            // Handle error
        }
    }

    public List<Match> getAllMatches() {
        List<Match> matches = new ArrayList<>();
        String query = "SELECT * FROM matches";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Match match = mapRowToMatch(resultSet);
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
        return matches;
    }

    public List<Match> getMatchesAfter(LocalDate date) {
        List<Match> matches = new ArrayList<>();
        String query = "SELECT * FROM matches WHERE match_day > ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, date.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Match match = mapRowToMatch(resultSet);
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
        return matches;
    }

    public List<Match> getMatchesBefore(LocalDate date) {
        List<Match> matches = new ArrayList<>();
        String query = "SELECT * FROM matches WHERE match_day < ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, date.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Match match = mapRowToMatch(resultSet);
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
        return matches;
    }

    public void setOpponent(String matchDay, String newOpponent){
        String updateQuery = "UPDATE matches set opponent = ? WHERE match_day = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newOpponent);
            preparedStatement.setString(2, matchDay);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in MatchManager: " + e.getMessage());
            // Handle error
        }
    }

    public void setCompetition(String matchDay, int competitionValue){
        String updateQuery = "UPDATE matches set competition = ? WHERE match_day = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, competitionValue);
            preparedStatement.setString(2, matchDay);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    public boolean setIsHome(String matchDay, int isHome){
        String updateQuery = "UPDATE matches set is_home = ? WHERE match_day = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, isHome);
            preparedStatement.setString(2, matchDay);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            // Handle error
        }
    }

    public void setIsFinished(String matchDay, int isFinished){
        String updateQuery = "UPDATE matches set is_finished = ? WHERE match_day = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, isFinished);
            preparedStatement.setString(2, matchDay);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    public void setScoredGoals(String matchDay, int scoredGoals){
        String updateQuery = "UPDATE matches set scored_goals = ? WHERE match_day = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, scoredGoals);
            preparedStatement.setString(2, matchDay);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    public void setOpponentGoals(String matchDay, int opponentGoals){
        String updateQuery = "UPDATE matches set scored_goals = ? WHERE match_day = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, opponentGoals);
            preparedStatement.setString(2, matchDay);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
    }

    public void setMatchDay(String oldMatchDay, String newMatchDay){
        String updateQuery = "UPDATE matches set scored_goals = ? WHERE match_day = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newMatchDay);
            preparedStatement.setString(2, oldMatchDay);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error
        }
    }


    // Helper method to map a ResultSet row to a Match object
    private Match mapRowToMatch(ResultSet resultSet) throws SQLException {
        String opponent = resultSet.getString("opponent");
        LocalDate matchDay = LocalDate.parse(resultSet.getString("match_day"));
        Competition competition = Competition.values()[resultSet.getInt("competition")];
        boolean isHome = resultSet.getBoolean("is_home");
        boolean isFinished = resultSet.getBoolean("is_finished");
        int scoredGoals = resultSet.getInt("scored_goals");
        int opponentGoals = resultSet.getInt("opponent_goals");

        Match match = new Match(opponent, matchDay, competition, isHome);
        match.setFinished(isFinished);
        match.setScoredGoals(scoredGoals);
        match.setOpponentGoals(opponentGoals);
        return match;
    }

}