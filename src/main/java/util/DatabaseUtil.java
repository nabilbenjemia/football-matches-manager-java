package util;

import java.sql.*;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/football_db";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "ncisbj"; // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver (optional with modern versions)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection and return it
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Database connection error: " + e.getMessage());
            throw new SQLException("Could not connect to the database.", e);
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String query_ = "insert into matches(opponent, match_day, competition, is_home) values (?, ?, ?, ?);";
            PreparedStatement prepareStatement = connection.prepareStatement(query_);
            prepareStatement.setString(1,"ESS");
            prepareStatement.setString(2, "2021-12-01");
            prepareStatement.setInt(3,2);
            prepareStatement.setBoolean(4,false);
            int rowsInserted = prepareStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new match was added successfully!");
            }

            String query = "select * from matches";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String opponent = resultSet.getString("opponent");
                String matchDay = resultSet.getString("match_day");
                int competition = resultSet.getInt("competition");
                boolean isHome = resultSet.getBoolean("is_home");
                boolean isFinished = resultSet.getBoolean("is_finished");
                int scoredGoals = resultSet.getInt("scored_goals");
                int opponentGoals = resultSet.getInt("opponent_goals");

                System.out.println("" +
                        "\nopponent: " + opponent +
                        "\nmatchDay: " + matchDay +
                        "\ncompetition: " + competition +
                        "\nisHome: " + isHome +
                        "\nisFinished: " + isFinished +
                        "\nscoredGoals: " + scoredGoals +
                        "\nopponentGoals: " + opponentGoals);
            }

        } catch (SQLException e) {
            System.out.println("Driver Not Found!");
            e.printStackTrace();
        }

    }
}


