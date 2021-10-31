import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class MovieApplication implements Serializable {

    //references

    private Scanner inputScanner;
    private static final String USER_FILE_NAME = "user-data.txt";
    //private static final String USER_MOVIE_LIST_FILE_NAME = "user-movie-data.txt";
    private InformationalDatabase database;
    private User activeUser;
    private UserLoginRegistration userLoginRegistration;
    private MovieSearch movieSearch;
    private Statistics accountStatistics;

    //instantiating the objects inside the constructor

    public MovieApplication(InformationalDatabase iDatabase) {
        this.inputScanner = new Scanner(System.in);
        this.database = new InformationalDatabase();
        this.activeUser = new User();
        this.userLoginRegistration = new UserLoginRegistration(database);
        this.userLoginRegistration.readUsersFromFile(USER_FILE_NAME);
        iDatabase.initializeMovieList();
//        this.movieSearch = new MovieSearch(database, inputScanner, activeUser);
//        this.accountStatistics = new Statistics(database);
    }

    //suggested but dont understand yet
    private void intializeUserRelatedObjects(){
        this.movieSearch = new MovieSearch(database, inputScanner, activeUser);
        this.accountStatistics = new Statistics(database);
    }

    public void displayMenu() {
        System.out.println("--- MOVIE APP MENU ---");
        System.out.println("Press 0 to list all movies in the application");
        System.out.println("Press 1 to search for a movie");
        System.out.println("Press 2 to see the account statistics");
        System.out.println("Press 3 to save a movie as favorite");
        System.out.println("Press 4 to list all favorite movies");
        System.out.println("Press 5 to remove a movie from your favourite list");
        System.out.println("Press 6 to quit");
        System.out.println("---------------------");
    }


    public void askForCredentials() {
        System.out.println("Are you a registered user or not (Y/N) ?");
        String userChoice = inputScanner.next();
        while (!userChoice.equalsIgnoreCase("Y") && !userChoice.equalsIgnoreCase("N")) {
            System.out.println("--- ERROR: Invalid input, please try again!");
            System.out.println("Are you a registered user or not (Y/N) ?");
            userChoice = inputScanner.next();
        }
        if (userChoice.equalsIgnoreCase("y")) {
            this.activeUser = this.userLoginRegistration.login(inputScanner, activeUser);
        } else if (userChoice.equalsIgnoreCase("n")) {
            this.activeUser = this.userLoginRegistration.registerNewUser(inputScanner, USER_FILE_NAME);
        }
        intializeUserRelatedObjects();
    }


    public void run() {
        System.out.println("----- Welcome to the MOVIE APPLICATION -----");
        askForCredentials();
        int userMenuChoice = -1;
        while (userMenuChoice != 6) {
            displayMenu();
            userMenuChoice = inputScanner.nextInt();
            if(userMenuChoice == 0){
                this.accountStatistics.displayAllMovies();
            }
            if (userMenuChoice == 1) {
                this.movieSearch.searchForMovie();
            }
            if (userMenuChoice == 2) {
                this.accountStatistics.displayAccountStatistics(activeUser);
            }
            if (userMenuChoice == 3) {
                this.movieSearch.searchForMovie();
            }
            if (userMenuChoice == 4) {
                this.movieSearch.listAllFavoriteMovies();
            }
            if(userMenuChoice == 5){
                this.movieSearch.removeMovieFromFavourites();
            }
            if (userMenuChoice == 6) {
                System.out.println("--- SAD TO SEE YOU GO ---");
                break;
            }
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InformationalDatabase database = new InformationalDatabase();
        File file = new File("databaseFile.ser");
/*

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(database);
        oos.flush();
        fos.close();
        oos.close();

*/

        //deserialize
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);


        InformationalDatabase iDatabase = (InformationalDatabase) ois.readObject();
        fis.close();
        ois.close();

        MovieApplication movieApplication = new MovieApplication(iDatabase);

        iDatabase.initializeMovieList();
        movieApplication.run();

    }

}
