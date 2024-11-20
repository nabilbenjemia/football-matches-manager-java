package Model;

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
    public Match(String opponent, LocalDate matchDay, Competition competition, boolean isHome) {
        this.opponent = opponent;
        this.matchDay = matchDay;
        this.competition = competition;
        this.isHome = isHome;
        this.isFinished = false;
        this.scoredGoals = ZERO;
        this.opponentGoals = ZERO;
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
