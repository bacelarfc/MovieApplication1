import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MovieSearch {

    private InformationalDatabase database;
    private Scanner inputScanner;
    private User activeUser;
    private static final String MOVIE_RECORDS_FILE = "movie-records.txt";


    //parameters to:
    public MovieSearch(InformationalDatabase IDatabase, Scanner inputScanner, User activeUser) {
        this.database = IDatabase;
        this.inputScanner = inputScanner;
        this.activeUser = activeUser;
    }

    public void searchForMovie() {
        System.out.println("Pick one criteria to search by");
        System.out.println("Press 1 for searching by title");
        System.out.println("Press 2 for searching by production year");
        System.out.println("Press 3 for searching by an actor");
        System.out.println("Press 4 to search by keyword");
        int userChoice = inputScanner.nextInt();
        switch (userChoice) {
            case 1:
                searchByTitle();
                break;
            case 2:
                searchByProductionYear();
                break;
            case 3:
                searchByActor();
                break;
            case 4:
                searchByKeywords();
                break;
            default:
                System.out.println(" *** INVALID INPUT, RETURNING TO MAIN MENU ***");
                break;
        }
    }


    // Passing an argument of list of movies that was returned as a result of some search (either by keyword, or by title, or by prod year)
    private void processMovieResult(ArrayList<Movie> movieArrayList) {
        // The search result returned line is going to count how many movies were found and it returns you that numbers below
        System.out.println(" --- SEARCH RETURNED " + movieArrayList.size() + " RESULT: --- ");
        for (Movie movie : movieArrayList) {
            System.out.println(movie.toString());
            System.out.println("---------------------");
        }
        if (movieArrayList.size() > 0) {
            System.out.println("Press 1 to play a movie");
            System.out.println("Press 2 to add movie to favourites");
            System.out.println("Press 3 to return to main menu");

            int userChoice = inputScanner.nextInt();

            if (userChoice == 3) {
                return;
            }
            Movie movieChoice = null;

            System.out.println("Input the title of the movie >> ");
            String title = getUserFullLineInput();

            for (Movie movie : movieArrayList) {
                if (movie.getTitle().equalsIgnoreCase(title)) {
                    movieChoice = movie;
                }
            }
            if (movieChoice == null) {
                System.out.println(" *** SEARCH DIDN'T RETURN ANY RESULT, RETURNING TO MAIN MENU *** ");
                return;
            }
            if (userChoice == 1) {
                //relate the user to access the movie history
                //add a date to it. (?
                //save the info to user file(?
                //activeUser.getMovieHistory().add(new MovieHistory(movieChoice, Date.from(Instant.now()), activeUser));

                MovieHistory history = new MovieHistory(movieChoice, Date.from(Instant.now()), activeUser);
                activeUser.getMovieHistory().add(history);
                try {
                   saveMovieHistoryToFile(history);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("..... PLAYING A MOVIE '" + movieChoice.getTitle() + "' .....");
                ArrayList<Actor> actorArrayList = movieChoice.getActors();
                System.out.println("................... ACTORS .......................");
                for (Actor actor : actorArrayList) {
                    System.out.println(actor.toString());
                }
                System.out.println("..................................................");
                return;
            } else if (userChoice == 2) {
                System.out.println("..... ADDING THE MOVIE TO THE FAV LIST .....");
                saveMovieAsFavorite(movieChoice);
            }
        }
    }

    public void saveMovieHistoryToFile(MovieHistory movieRecord) throws IOException {
        File file = new File(MOVIE_RECORDS_FILE);
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(movieRecord.toString());
        fileWriter.close();
    }
    public void saveMovieAsFavorite(Movie movie) {
        for (Movie favMovies : activeUser.getFavMovieList()) {
            if (favMovies.getTitle().equalsIgnoreCase(movie.getTitle())) {
                System.out.println(" --- THE MOVIE '" + movie.getTitle() + "' IS ALREADY IN YOUR FAVOURITE LIST ---");
                return;
            }
        }
        activeUser.getFavMovieList().add(movie);
    }

    public void listAllFavoriteMovies() {
        System.out.println("----- LISTING FAVOURITE MOVIES FOR USER '" + activeUser.getName() + "' -----");
        for (Movie movie : activeUser.getFavMovieList()) {
            System.out.println(movie);
        }
        System.out.println("--------------------");
    }

    private String getUserFullLineInput() {
        String userInput = inputScanner.nextLine();
        userInput = inputScanner.nextLine();
        return userInput;
    }

    private void searchByTitle() {
        System.out.println("Input a title of the movie: >> ");
        String title = getUserFullLineInput();
        for (Movie movie : database.getMovies()) {
            if (movie.getTitle().contains(title)) {
                ArrayList<Movie> resultList = new ArrayList<>();
                resultList.add(movie);
                processMovieResult(resultList);
                return;
            }
        }
        System.out.println(" *** SEARCH DIDN'T RETURN ANY RESULT, RETURNING TO MAIN MENU *** ");
    }


    private void searchByProductionYear() {
        System.out.println("Input a production year of the movie: >> ");
        int prodYear = inputScanner.nextInt();
        ArrayList<Movie> resultList = new ArrayList<>();

        for (Movie movie : database.getMovies()) {
            if (movie.getProdYear() == (prodYear)) {
                resultList.add(movie);
            }
        }
        processMovieResult(resultList);

    }

    private void searchByActor() {
        System.out.println("Input an actor name to search a movie by: >> ");
        String actorName = getUserFullLineInput();
        ArrayList<Movie> resultList = new ArrayList<>();
        for (Movie movie : database.getMovies()) {
            ArrayList<Actor> actorArrayList = movie.getActors();
            for (Actor actor : actorArrayList) {
                if (actor.getFullName().equals(actorName)) {
                    resultList.add(movie);
                }
            }
        }
        processMovieResult(resultList);

    }

    private void searchByKeywords() {
        System.out.println("Input keywords: >> ");
        String keyword = getUserFullLineInput();
        ArrayList<Movie> resultList = new ArrayList<>();
        for (Movie movie : database.getMovies()) {
            if (movie.getKeyWords().contains(keyword)) {
                resultList.add(movie);

            }

            processMovieResult(resultList);
        }

        System.out.println(" *** SEARCH DIDN'T RETURN ANY RESULT, RETURNING TO MAIN MENU *** ");
    }

    //check which movie user wants to delete - get input and save in a var
    //loop to find mov list inside user
    // check if movie its there inside and compare move name with the input
    //use active user favorite list. remove the movie matched

    public void removeMovieFromFavourites() {
        System.out.println("Input a title of the movie that you would like removed from your fav list: ");
        String movieName = inputScanner.nextLine();
        movieName = inputScanner.nextLine();
        System.out.println(" --- SEARCHING FOR " + movieName + " IN YOUR FAVOURITE LIST..... --- ");
        for (Movie movie : activeUser.getFavMovieList()) {
            if (movie.toString().equalsIgnoreCase(movieName)) {
                activeUser.getFavMovieList().remove(movie);
                System.out.println(" --- ... REMOVING A MOVIE FROM THE LIST..... --- ");
                return;
                //break out the loop after - could have used a boolean variable, and set it to false instead
            }
        }
        System.out.println(" --- THE SEARCH DIDN'T RETURN ANY RESULT, TRY AGAIN LATER --- ");
    }
}
