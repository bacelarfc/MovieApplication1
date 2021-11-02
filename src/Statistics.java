
import java.io.*;
import java.util.ArrayList;

public class Statistics implements Serializable {

//to know when user has seen a movie
    //has access to the database to check

    private InformationalDatabase iDatabase;

    public Statistics(InformationalDatabase database){
        this.iDatabase = database;
    }

        public void displayAccountStatistics(User activeUser) {
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("M O V I E   H I S T O R Y");

        for(MovieHistory movieRecord : activeUser.getMovieHistory()){
            System.out.println("The movie " + movieRecord.getMovie().getTitle() + " was played on " + movieRecord.getDatePlayed()+ " by " + activeUser.getName());
        }

        System.out.println("-----------------------");

    }


    public void displayAllMovies() {
        System.out.println("----- SELECTING ALL MOVIES FROM THE DATABASE -----");
        for(Movie movie : iDatabase.getMovies()){
            System.out.println(movie);
            System.out.println("-----");
        }
        System.out.println("--------------------");
    }


}
