import java.io.Serializable;
import java.util.ArrayList;

public class InformationalDatabase implements Serializable {

    private ArrayList<Movie> movies;
    private ArrayList<User> users;

    public InformationalDatabase() {
        this.movies = new ArrayList<>();
        this.users = new ArrayList<>();
       initializeMovieList();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void initializeMovieList() {
        Movie movie1 = new Movie("Find Nemo", 2003, "Dead parents, animation, fish");
        Actor actor1ForMovie1 = new Actor("Ellen DeGeneres", "Dory");
        Actor actor2ForMovie1 = new Actor("Albert Brookis", "Marlin");
        movie1.addNewActor(actor1ForMovie1);
        movie1.addNewActor(actor2ForMovie1);
        movies.add(movie1);

        Movie movie2 = new Movie("Harry Potter and the Philosopher’s Stone", 2001, "Dead parents, magic, glasses");
        Actor actor1ForMovie2 = new Actor("Daniel Radcliff", "Harry Potter");
        Actor actor2ForMovie2 = new Actor("Emma Watson", "Hermione Granger");
        movie2.addNewActor(actor1ForMovie2);
        movie2.addNewActor(actor2ForMovie2);
        movies.add(movie2);

        Movie movie3 = new Movie("Forrest Gump", 1994, "Vietnam War, smiley");
        Actor actor1ForMovie3 = new Actor("Tom Hanks", "Forrest Gump");
        Actor actor2ForMovie3 = new Actor("Robin Wright", "Jenny Curran");
        movie3.addNewActor(actor1ForMovie3);
        movie3.addNewActor(actor2ForMovie3);
        movies.add(movie3);

        Movie movie4 = new Movie("The Shawshank Redemption", 1994, "Prison, freedom, god");
        Actor actor1ForMovie4 = new Actor("Tim Robbins", "Andy Dufresne");
        Actor actor2ForMovie4 = new Actor("Morgan Freeman", "Ellis Boyd 'Red' Redding");
        movie4.addNewActor(actor1ForMovie4);
        movie4.addNewActor(actor2ForMovie4);
        movies.add(movie4);

        Movie movie5 = new Movie("The Fast and Furious: Tokyo Drift", 2006, "Cars, drifting, Japan, dead, person");
        Actor actor1ForMovie5 = new Actor("Lucas Black", "Sean Boswell");
        Actor actor2ForMovie5 = new Actor("Sung Kang", "Han Lue");
        movie5.addNewActor(actor1ForMovie5);
        movie5.addNewActor(actor2ForMovie5);
        movies.add(movie5);

        Movie movie6 = new Movie("Bambi", 1942, "Dead parents, animals, sad");
        Actor actor1ForMovie6 = new Actor("Hardie Albright", "Bambi");
        Actor actor2ForMovie6 = new Actor("Stan Alexander", "Flower");
        movie6.addNewActor(actor1ForMovie6);
        movie6.addNewActor(actor2ForMovie6);
        movies.add(movie6);

        Movie movie7 = new Movie("Air Bud", 1997, "Dog, basketball, sad");
        Actor actor1ForMovie7 = new Actor("Michael Jeter", "Norm Snively");
        Actor actor2ForMovie7 = new Actor("Kevin Zegers", "Josh Framm");
        movie7.addNewActor(actor1ForMovie7);
        movie7.addNewActor(actor2ForMovie7);
        movies.add(movie7);

        Movie movie8 = new Movie("Parasite", 2019, "Capitalism, social inequality");
        Actor actor1ForMovie8 = new Actor("Kang-Ho Song", "Ki Taek");
        Actor actor2ForMovie8 = new Actor("Sun-Kyun Lee", "Dong Ik");
        movie8.addNewActor(actor1ForMovie8);
        movie8.addNewActor(actor2ForMovie8);
        movies.add(movie8);

        Movie movie9 = new Movie("Big Momma's House", 2000, "Fart, Momma");
        Actor actor1ForMovie9 = new Actor("Martinn Lawrence", "Malcolm Turner/Big Momma");
        Actor actor2ForMovie9 = new Actor("Nia Long", "Sherry Pierce");
        movie9.addNewActor(actor1ForMovie9);
        movie9.addNewActor(actor2ForMovie9);
        movies.add(movie9);

        Movie movie10 = new Movie("Hot Fuzz", 2007, "British, cops");
        Actor actor1ForMovie10 = new Actor("Simon Pegg", "Nicholas Angel");
        Actor actor2ForMovie10 = new Actor("Nick Frost", "PC Danny Butterman");
        movie10.addNewActor(actor1ForMovie10);
        movie10.addNewActor(actor2ForMovie10);
        movies.add(movie10);

    }

    // Method to add users to a userlist, adding the user
    // will happen upon registration or by reading all users from an existing file (user-data.txt)
    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
