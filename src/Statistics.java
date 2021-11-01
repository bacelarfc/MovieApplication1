
import java.io.*;

public class Statistics implements Serializable {
//to know when user has seen a movie
    //has access to the database to check

    private InformationalDatabase iDatabase;

    public Statistics(InformationalDatabase database){
        this.iDatabase = database;
    }

    //This is a bit mess, I thought I can fix it with some help but not yet :'|
    public void saveMovieHistory (User user) {
        try {
            FileWriter movieHistoryWriter = new FileWriter("movie-History-" + user.getName() + ".txt", false);
            for (int i = 0; i < user.getMovieHistory().size(); i++) {
                
            }
           user.getMovieHistory().get(0).getMovie().getTitle();
           // String movieInfo = movie.getTitle() + ", " + movie.getActors() +"\n";
           // movieHistoryWriter.write(user.getMovieHistory());
            movieHistoryWriter.close();

        } catch (IOException e) {
            System.out.println("ERROR - file not found");
        }

    }
     //Ask Saint Christian.
    /*public void movieReadFromFile(String movieHistory) {
        try{
            File file = new File(movieHistory);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = " ";
            while ((line = br.readLine()) != null){



            }

        }catch (Exception e){

        }

    }*/


        public void displayAccountStatistics(User activeUser) {

        //maybe a message(?
        //loop to find the movie history inside the user
        //print movie and get the title maybe also get the movie history name and the dates stuff, use the parameters there ???? ufck

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
