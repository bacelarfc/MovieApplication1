package helpers;

import java.util.Scanner;

public class CinemaInputHelper {
    public static int getMovieAppMenuOption(Scanner inputScanner) {
        System.out.println("\n\n");
        System.out.println("--- MOVIE APP MENU ---");
        System.out.println("Press 0 to list all movies in the application");
        System.out.println("Press 1 to search for a movie");
        System.out.println("Press 2 to see the account statistics");
        System.out.println("Press 3 to list all favorite movies");
        System.out.println("Press 4 to remove a movie from your favourite list");
        System.out.println("Press 5 to quit");
        System.out.println("What option do you want to select:");
        return inputScanner.nextInt();
    }
}
