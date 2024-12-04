package View;

import Model.Match;

import java.util.List;
import java.util.Scanner;

public class MatchView {

    public void displayMenu() {

    }

    public int getUserChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Choose a number : \n" +
                    "- 0 -> Register As an Administrator\n" +
                    "- 1 -> View upcoming matches\n" +
                    "- 2 -> View Old Matches\n");
        int input = scanner.nextInt();

        return input;
    }





    public void showError(String error) {
        System.out.println(error);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void displayMatches(List<Match> upcomingMatches) {
        System.out.println("**Upcomping Matches:** \n");
        for ( Match match: upcomingMatches) {
            System.out.println(match + "\n");
        }
    }
}
