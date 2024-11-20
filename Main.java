import Model.Competition;
import Model.MatchManager;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Model.Match(String opponent, LocalDate matchDay, Model.Competition competition, boolean isHome, boolean isFinished)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the name of the Opponent: ");
        String opponent = scanner.nextLine();
        System.out.println("Please provide the MatchDay in the form yyyy-mm-dd");
        String matchDay = scanner.nextLine();
        System.out.println("Please choose the competition: \n1.LEAGUE,\n" +
                "2.CUP,\n" +
                "3.CHAMPIONS LEAGUE,\n" +
                "4.FRIENDLY" +
                "Choose a number from 1 to 4");
        int number = scanner.nextInt();
        Competition competition;

        switch (number) {
            case 1:
                competition = Competition.LEAGUE;
                break;
            case 2:
                competition = Competition.CUP;
                break;
            case 3:
                competition = Competition.CHAMPIONS_LEAGUE;
                break;
            case 4:
                competition = Competition.FRIENDLY;
                break;
            default:
                competition = Competition.FRIENDLY;
                System.out.println("Invalid number: " + number);
        }
        System.out.println("Is the Model.Match played at 1.Home or 2.Away? Choose a number");
        int number2 = scanner.nextInt();
        boolean isHome = number2 != 2;

        MatchManager matchManager = new MatchManager();
        matchManager.addMatch(opponent, LocalDate.parse(matchDay), competition, isHome);


        System.out.println(matchManager.toString());
    }
}
