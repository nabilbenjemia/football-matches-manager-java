package View;

import Model.Competition;
import Model.Match;
import com.sun.source.tree.BreakTree;

import javax.swing.*;
import java.awt.*;
import java.io.Console;
import java.io.IOException;
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
                    "- 2 -> View Old Matches\n" +
                "- 3 -> View all Matches");
        int input = scanner.nextInt();

        return input;
    }

    public int displayAdminChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Choose a number : \n" +
                "- 0 -> Add a Match\n" +
                "- 1 -> Update result\n" +
                "- 2 -> Change Date\n" +
                "- 3 -> Remove Date");
        int input = scanner.nextInt();

        return input;
    }

    public String getOpponent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Opponent: ");
        String opponent = scanner.nextLine();

        return opponent;
    }

    public String getMatchDay() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("MatchDay: ");
        String matchDay = scanner.nextLine();
        return matchDay;
    }

    public Competition getCompetition() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Choose a number : \n" +
                "- 0 -> League\n" +
                "- 1 -> Cup\n" +
                "- 2 -> Champions League\n" +
                "- 3 -> Friendly");
        int input = scanner.nextInt();
        Competition competition = null;
        switch (input) {
            case 0 -> competition = Competition.LEAGUE;
            case 1 -> competition = Competition.CUP;
            case 2 -> competition = Competition.CHAMPIONS_LEAGUE;
            case 3 -> competition = Competition.FRIENDLY;
            default -> {
                System.out.println("Wrong Input");
                getCompetition();
            }
        }
        return competition;
    }

    public boolean isHome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Played at Home?\n" +
                "Please Choose a number : \n" +
                "0 -> Home\n" +
                "1 -> Away");
        int input = scanner.nextInt();
        if(input == 0) {
            return true;
        } else if(input == 1) {
            return false;
        } else {
            System.out.println("Wrong input");
        }
        return false;
    }





    public void showError(String error) {
        System.out.println(error);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void displayMatches(List<Match> upcomingMatches) {
        System.out.println("**Results:**");
        for ( Match match: upcomingMatches) {
            System.out.println(match);
        }
    }

    public String[] getAdministratorCredentials() {
        String[] credentials = new String[2];
        Scanner scanner = new Scanner(System.in);

        // Get username
        System.out.print("Username: ");
        String username = scanner.nextLine();
        credentials[0] = username;

        // Get password with masking
        System.out.print("Password: ");
        String password = scanner.nextLine();
        credentials[1] = password;

        return credentials;
    }

    public String[] getNewMatchDay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Old Matchday (yyyy-mm-dd");
        String oldMatchDay = scanner.next();
        System.out.println("New Matchday (yyyy-mm-dd");
        String newMatchDay = scanner.next();

        String[] input = new String[2];
        input[0] = oldMatchDay;
        input[1] = newMatchDay;
        return input;

    }



}
