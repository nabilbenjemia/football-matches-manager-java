package Model;

import Model.Competition;
import Model.Match;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class MatchManager {

    private List<Match> listOfMatches;

    public MatchManager() {
        this.listOfMatches = new LinkedList<>();
    }

    public void addMatch(String opponent, LocalDate matchDay, Competition competition, boolean isHome) {
        for (Match addedMatch : listOfMatches) {
            if (matchDay.equals(addedMatch.getMatchDay())) {
                if(opponent.equals(addedMatch.getOpponent())) {
                    System.out.println("This Model.Match has already been added");
                } else {
                    System.out.println("On this day, a Model.Match already exists");
                }
                return;
            }
        }
        Match match = new Match(opponent, matchDay, competition, isHome);
        listOfMatches.add(match);
    }

    public void removeMatch(LocalDate matchDay) {
        int matchIndex = getMatchIndex(matchDay);
        if(matchIndex >= 0){
            listOfMatches.remove(matchIndex);
        }
    }

    public void removeMatch(Match match) {
        listOfMatches.remove(match);
    }

    private int getMatchIndex(LocalDate matchDay) {
        for (int i = 0; i < listOfMatches.size(); i++) {
            if (listOfMatches.get(i).getMatchDay().equals(matchDay)) {
                return i;
            }
        }
        return -1;
    }

    public void updateMatchDay(LocalDate oldMatchDay, LocalDate newMatchDay) {
        int matchIndex = getMatchIndex(oldMatchDay);
        if(matchIndex >= 0) {
            listOfMatches.get(matchIndex).setMatchDay(newMatchDay);
        }
    }

    public void setMatchFinished(LocalDate oldMatchDay) {
        int matchIndex = getMatchIndex(oldMatchDay);
        if(matchIndex >= 0) {
            listOfMatches.get(matchIndex).setFinished(true);
        }
    }

    public void setScoredGoals(LocalDate oldMatchDay, int scoredGoals) {
        int matchIndex = getMatchIndex(oldMatchDay);
        if(matchIndex >= 0) {
            listOfMatches.get(matchIndex).setScoredGoals(scoredGoals);
        }
    }

    public void setOpponentGoals(LocalDate oldMatchDay, int opponentGoals) {
        int matchIndex = getMatchIndex(oldMatchDay);
        if(matchIndex >= 0) {
            listOfMatches.get(matchIndex).setOpponentGoals(opponentGoals);
        }
    }
    public String toString() {
        String str = "";
        for (Match match: listOfMatches) {
            str += match.toString() + "\n";
        }
        return str;
    }

    public Match getMatch(LocalDate matchDay) {
        return listOfMatches.get(getMatchIndex(matchDay));
    }

    public int getTotalNumberOfMatchs() {
        return listOfMatches.size();
    }
    /*
    public void viewTasks(boolean onlyDone) {
        boolean isEmpty = true;
        if(onlyDone) {
            for (Model.Match match : listOfTasks) {
                if (match.getDone()) {
                    isEmpty = false;
                    System.out.println(match);
                }
            }
        } else {
            for (Model.Match match : listOfTasks) {
                isEmpty = false;
                System.out.println(match);
            }
        }
        if(isEmpty) {
            System.out.println("There are no tasks here");
        }
    }

     */
}
