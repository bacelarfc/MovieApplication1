import helpers.CinemaInputHelper;
import helpers.MovieHelper;
import helpers.UserInputHelper;
import models.*;
import repositories.RepositoryCinema;
import services.MovieService;
import services.UserService;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MovieApplication {

    private Cinema cinemaDb;
    private final RepositoryCinema repositoryCinema;
    private final MovieService movieService;
    private final UserService userService;
    //making references
    private Scanner inputScanner;
    //final: it's a "constant" value which is same across all the class instances and cannot be modified.
    private static final String USER_FILE_NAME = "user-data.txt";
    //maybe
    private Cinema cinema;
    private User activeUser;
    private MovieSearch movieSearch;

    //instantiating the objects inside the constructor so we dont need to create an object every time

    public MovieApplication() throws IOException, ClassNotFoundException {
        this.inputScanner = new Scanner(System.in);
        this.repositoryCinema = new RepositoryCinema();
        this.cinemaDb = repositoryCinema.getCinemaFrom();
        this.movieService = new MovieService();
        this.userService = new UserService();
    }

    //this runs our application, it calls on the methods askForCredentials and mainMenu
    public void run() throws Exception {
        System.out.println("----- Welcome to the MOVIE APPLICATION -----");

        verifyCredentials();
        mainMenu();
    }

    public void verifyCredentials() throws Exception {
        if (UserInputHelper.checkIfUserIsRegistered(this.inputScanner) == true) {
            String name = UserInputHelper.askName(this.inputScanner);
            this.activeUser = this.userService.findUserByName(cinemaDb, name);
            if (this.activeUser == null) {
                System.out.println("User doesn't exist, we will try again.");
                verifyCredentials();
            }
        } else {
            createNewUser();
        }
    }

    private void createNewUser() throws Exception {
        User user = UserInputHelper.getNewUserInput(inputScanner);

        this.activeUser = this.userService.findUserByName(cinemaDb, user.getName());
        if (this.activeUser != null) {
            System.out.println("The user is already taken, please, try again.");
            this.verifyCredentials();
        } else {
            // this is a new user, we should save it before losing it.
            this.cinemaDb.getUsers().add(user);
            this.repositoryCinema.saveCinema(cinemaDb);
        }
    }

    //this method uses a while loop that calls on methods related to the displayed option
    public void mainMenu() {
        try {
            int userMenuChoice = -1;

            while (userMenuChoice != 5) {
                userMenuChoice = CinemaInputHelper.getMovieAppMenuOption(this.inputScanner);
                if (userMenuChoice == 0) {
                    MovieHelper.displayAllMovies(cinemaDb.getMovies());
                }
                if (userMenuChoice == 1) {
                    int selectedOption = MovieHelper.getMovieSearchOptions(this.inputScanner);
                    ArrayList<Movie> results = searchForMovies(selectedOption);
                    this.processMovieResult(results);
                }
                if (userMenuChoice == 3) {
                    User user = this.userService.findUserByName(cinemaDb, this.activeUser.getName());
                    MovieHelper.displayAllMovies(user.getFavMovieList());
                }

            }
        } catch (Exception e) {

            System.out.println("Something happened!");
        }
    }

    private ArrayList<Movie> searchForMovies(int selectedOption) {
        ArrayList<Movie> movies = null;

        if (selectedOption == 1) {
            String movie = MovieHelper.getTitle(this.inputScanner);
            movies = movieService.searchByTitle(cinemaDb, movie);
        }
        if (selectedOption == 2) {
            int year = MovieHelper.getYear(this.inputScanner);
            movies = movieService.searchByProductionYear(cinemaDb, year);
        }
        if (selectedOption == 3) {
            String actor = MovieHelper.getActor(this.inputScanner);
            movies = movieService.searchByActor(cinemaDb, actor);
        }
        if (selectedOption == 4) {
            String keyWord = MovieHelper.getKeyword(this.inputScanner);
            movies = movieService.searchByKeywords(cinemaDb, keyWord);
        }

        return movies;
    }

    private void processMovieResult(ArrayList<Movie> moviesResults) throws IOException {
        // The search result returned line is going to count how many movies were found and it returns you that numbers below
        System.out.println(" --- SEARCH RETURNED " + moviesResults.size() + " RESULT: --- ");
        if (moviesResults.size() > 0) {
            MovieHelper.displayAllMovies(moviesResults);

            int userChoice = MovieHelper.actionsWithMovie(this.inputScanner);
            if (userChoice == 3) {
                return;
            }

            Movie movieChoice = moviesResults.get(0);
            if (userChoice == 1) {
                MovieHelper.playMovie(movieChoice);
            }
            if (userChoice == 2) {
                cinemaDb = userService.addMovieToFavourite(cinemaDb, this.activeUser, movieChoice);
                this.repositoryCinema.saveCinema(cinemaDb);
                MovieHelper.playMovie(movieChoice);
            } else if (userChoice == 2) {
//                System.out.println("..... ADDING THE MOVIE TO THE FAV LIST .....");
//                saveMovieAsFavorite(movieChoice);
            }
        }
    }

}
//hello