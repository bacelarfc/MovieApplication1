public class Statistics {

    private InformationalDatabase iDatabase;

    public Statistics(InformationalDatabase database){
        this.iDatabase = database;
    }


    public void displayAccountStatistics(User activeUser) {

        //maybe a message(?
        //loop to find the movie history inside the user
        //print movie and get the title maybe also get the movie history name and the dates stuff, use the parameters there ???? ufck
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
