package Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.*;

public class Match {

    private static final int ZERO = 0;
    private String opponent;
    private LocalDate matchDay;
    private boolean isHome;
    private boolean isFinished;
    private int scoredGoals;
    private int opponentGoals;
    private Competition competition;

    //Constructor
    @JsonCreator
    public Match(@JsonProperty("opponent") String opponent,
                 @JsonProperty("date") LocalDate matchDay,
                 @JsonProperty("competition") Competition competition,
                 @JsonProperty("home") boolean isHome) {
        this.opponent = opponent;
        this.matchDay = matchDay;
        this.competition = competition;
        this.isHome = isHome;
        this.isFinished = false;
        this.scoredGoals = ZERO;
        this.opponentGoals = ZERO;
    }

    // Custom constructor that accepts JSON as a string
    public Match(String matchJSON) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(matchJSON);

        this.opponent = rootNode.get("opponent").asText();
        this.matchDay = LocalDate.parse(rootNode.get("date").asText());

        // Handling competition as an enum from a string
        int competitionString = rootNode.get("competition").asInt();
        if(competitionString >= 0 && competitionString <= 3) {
            this.competition = Competition.values()[competitionString];
        } else {
            this.competition = Competition.FRIENDLY;
        }

        // Handling 'home' as a boolean
        this.isHome = rootNode.get("home").asBoolean();

        // Default values for isFinished, scoredGoals, and opponentGoals
        this.isFinished = false;
        this.scoredGoals = 0;
        this.opponentGoals = 0;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public LocalDate getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(LocalDate matchDay) {
        this.matchDay = matchDay;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getOpponentGoals() {
        return opponentGoals;
    }

    public void setOpponentGoals(int opponentGoals) {
        this.opponentGoals = opponentGoals;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public String toString() {
        return "Model.Match{" +
                "opponent='" + opponent + '\'' +
                ", matchDay=" + matchDay.toString() +
                ", isHome=" + isHome +
                ", isFinished=" + isFinished +
                ", scoredGoals=" + scoredGoals +
                ", opponentGoals=" + opponentGoals +
                ", competition=" + competition +
                '}';
    }
}
