package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    //Models.User class has:

    private String name;
    private String email;
    private String password;
    //associate a favMovieList that contains movie objects here
    private List<Movie> favMovieList;
    //associate movieHistory that it's contained inside Models.MovieHistory class, so each user has a movie history record.
    private List<MovieHistory> movieHistory;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.favMovieList = new ArrayList<>();
        this.movieHistory = new ArrayList<>();
        // this.userStatistics = new ArrayList<>();
    }

    // DEFAULT constructor
    public User() {
        this.email = "";
        this.password = "";
        this.name = "";
        this.favMovieList = new ArrayList<>();
        this.movieHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Movie> getFavMovieList() {
        return favMovieList;
    }

    public void setFavMovieList(List<Movie> favMovieList) {
        this.favMovieList = favMovieList;
    }

    public List<MovieHistory> getMovieHistory() {
        return movieHistory;
    }

    public void setMovieHistory(List<MovieHistory> movieHistory) {
        this.movieHistory = movieHistory;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", Email: " + this.email;
    }
}
