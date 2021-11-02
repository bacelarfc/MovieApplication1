package helpers;

import models.Actor;
import models.Cinema;
import models.Movie;

import java.util.List;
import java.util.Scanner;

public class MovieHelper {

    public static void displayAllMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }

    public static String getTitle(Scanner inputScanner) {
        System.out.println("What movie do you want to look?");
        return inputScanner.next();
    }

    public static int getYear(Scanner inputScanner) {
        System.out.println("What year do you want to look?");
        return inputScanner.nextInt();
    }

    public static String getActor(Scanner inputScanner) {
        System.out.println("What actor do you want to look?");
        return inputScanner.next();
    }

    public static String getKeyword(Scanner inputScanner) {
        System.out.println("What key word do you want to look?");
        return inputScanner.next();
    }

    public static int getMovieSearchOptions(Scanner inputScanner) {
        System.out.println("Pick one criteria to search by");
        System.out.println("Press 1 for searching by title");
        System.out.println("Press 2 for searching by production year");
        System.out.println("Press 3 for searching by an actor");
        System.out.println("Press 4 to search by keyword");
        return inputScanner.nextInt();
    }

    public static int actionsWithMovie(Scanner inputScanner) {
        System.out.println("Press 1 to play a movie");
        System.out.println("Press 2 to add movie to favourites");
        System.out.println("Press 3 to return to main menu");
        return inputScanner.nextInt();
    }

    public static void playMovie(Movie movie) {
        System.out.println("Here are all the actors:");
        for (Actor actor : movie.getActors()) {
            System.out.println(actor.toString());
        }
    }
}
