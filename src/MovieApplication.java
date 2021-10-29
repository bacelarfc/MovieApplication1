import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
//when to use serialize

public class MovieApplication implements Serializable {


    private Scanner inputScanner;
    private static final String USER_FILE_NAME = "user-data.txt";
    private InformationalDatabase database;
    private User activeUser;
    private Statistics movieHistory;

    public MovieApplication(InformationalDatabase iDatabase) {
        this.inputScanner = new Scanner(System.in);
        this.database = iDatabase;
        this.activeUser = new User();
        readUsersFromFile();
        iDatabase.initializeMovieList();
        movieHistory = new Statistics();
    }


    private String getUserFullLineInput() {
        String userInput = inputScanner.nextLine();
        userInput = inputScanner.nextLine();
        return userInput;
    }

    public void displayMenu() {
        System.out.println("--- MOVIE APP MENU ---");
        System.out.println("Press 1 to search for a movie");
        System.out.println("Press 2 to see the account statistics");
        System.out.println("Press 3 to save a movie as favorite");
        System.out.println("Press 4 to list all favorite movies");
        System.out.println("Press 5 to quit");
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
            login();
        } else if (userChoice.equalsIgnoreCase("n")) {
            registerNewUser();
        }
    }

    public void login() {
        System.out.print("Please input your email: --> ");
        String email = inputScanner.next();
        System.out.print("Please input your password: --> ");
        String password = inputScanner.next();
        System.out.println();
        boolean foundUser = false;
        for (User user : database.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                activeUser = user;
                foundUser = true;
            }
        }
        if (foundUser == true) {
            System.out.println(" --- GLAD TO SEE YOU BACK, " + activeUser.getName() + " ---");
        } else {
            System.out.println(" --- EMAIL OR PASSWORD DO NOT MATCH, TRY AGAIN ---");
            login();
        }
    }

    public void registerNewUser() {
        System.out.print("Please input your name: --> ");
        String name = inputScanner.nextLine();
        name = inputScanner.nextLine();
        System.out.println();
        System.out.print("Please input your email: --> ");
        String email = inputScanner.next();
        System.out.print("Please input your password: --> ");
        String password = inputScanner.next();
        System.out.println();
        User user = new User(name, email, password);
        //change
        if (checkIfUniqueUser(email) == true) {
            System.out.println(" --- SORRY, THIS EMAIL IS ALREADY TAKEN! --- ");
        } else {
            saveUserToFile(user);
            database.addUser(user);
        }

    }

    public boolean checkIfUniqueUser(String email) {
        if (database.getUsers().size() == 0) {
            return true;
        }
        for (User user : database.getUsers()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public void saveUserToFile(User user) {
        try {
            FileWriter fileWriter = new FileWriter(USER_FILE_NAME, true);
            String userInfo = user.getName() + "," + user.getEmail() + "," + user.getPassword() + "\n";
            fileWriter.write(userInfo);
            fileWriter.close();
        } catch (Exception exception) {
            System.out.println("ERROR: wrong file name or missing file!");
        }
    }

    public void readUsersFromFile() {
        try {
            File file = new File(USER_FILE_NAME);
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    String[] info = line.split(",");
                    String name = info[0];
                    String email = info[1];
                    String password = info[2];
                    User user = new User(name, email, password);
                    database.addUser(user);
                }
            } else {
                System.out.println("FILE DOESNT EXIST");
            }

        } catch (Exception exception) {
            System.out.println("ERROR: wrong file name or missing file!");
        }
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

    public void run() {
        System.out.println("----- Welcome to the MOVIE APPLICATION -----");
        askForCredentials();
        int userMenuChoice = -1;
        while (userMenuChoice != 5) {
            displayMenu();
            userMenuChoice = inputScanner.nextInt();
            if (userMenuChoice == 1) {
                searchForMovie();
            }
            if (userMenuChoice == 2) {
                displayAccountStatistics();
            }
            if (userMenuChoice == 3) {
                saveMovieAsFavorite();
            }
            if (userMenuChoice == 4) {
                listAllFavoriteMovies();
            }
            if (userMenuChoice == 5) {
                System.out.println("--- SAD TO SEE YOU GO ---");
                break;
            }
        }
    }

    public void searchForMovie() {
        System.out.println("Pick one criteria to search by");
        System.out.println("Press 1 for searching by title");
        System.out.println("Press 2 for searching by production year");
        System.out.println("Press 3 for searching by an actor");
        System.out.println("Press 4 to search by keywords");
        int userChoice = inputScanner.nextInt();
        try {
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
            default:
                System.out.println(" *** INVALID INPUT, RETURNING TO MAIN MENU ***");
                break;
        }
    } catch (Exception e) {
            System.out.println("Error");
        }
    }




    private void processMovieResult(ArrayList<Movie> movieArrayList) {
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
           /* System.out.println("Input the title of the movie >> ");
            String title = getUserFullLineInput();*/

            Movie movieChoice = null;

            for (Movie movie : movieArrayList) {
                //if (movie.getTitle().equalsIgnoreCase(title)) {
                    movieChoice = movie;
               // }

            }

            if (movieChoice == null) {
                System.out.println(" *** SEARCH DIDN'T RETURN ANY RESULT, RETURNING TO MAIN MENU *** ");
                return;
            }

            if (userChoice == 1) {
                System.out.println("..... PLAYING A MOVIE '" + movieChoice.getTitle() + "' .....");
                ArrayList<Actor> actorArrayList = movieChoice.getActors();
                System.out.println("................... ACTORS .......................");
                for (Actor actor : actorArrayList) {
                    System.out.println(actor.toString());
                }
                System.out.println("..................................................");
                //
                return;
            } else if (userChoice == 2) {
                System.out.println("..... ADDING THE MOVIE TO THE FAV LIST .....");
                // add this info to a file ?
                activeUser.getFavMovieList().add(movieChoice);
            } else if (userChoice == 3) {
                return;
            }
        }


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
                if (actor.getFullName().contains(actorName)) {
                    resultList.add(movie);
                }
            }
        }
        processMovieResult(resultList);

    }


    public void displayAccountStatistics() {


    }

    public void saveMovieAsFavorite() {


    }

    public void listAllFavoriteMovies() {

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InformationalDatabase database = new InformationalDatabase();
        File file = new File("database.ser");



       /* FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(database);
        oos.flush();
        fos.close();
        oos.close();*/


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
