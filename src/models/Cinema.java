package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {
    //has a array of movie objects and user objects
    private ArrayList<Movie> movies;
    private ArrayList<User> users;
    //call the initialized movie list inside, so it can get initialized by the constructor

    public Cinema() {
        this.movies = new ArrayList<>();
        this.users = new ArrayList<>();
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

    public String toString() {
        return "Models.InformationalDatabase " +
                "movies=" + movies +
                ", users=" + users;
    }
}