package services;

import models.Actor;
import models.Cinema;
import models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService {

    public ArrayList<Movie> searchByTitle(Cinema databaseDb, String title) {
        ArrayList<Movie> results = new ArrayList<>();

        for (Movie movie : databaseDb.getMovies()) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                results.add(movie);
            }
        }

        return results;
    }

    public ArrayList<Movie>  searchByProductionYear(Cinema databaseDb, int year) {
        ArrayList<Movie> results = new ArrayList<>();

        for (Movie movie : databaseDb.getMovies()) {
            if (movie.getProdYear() == year) {
                results.add(movie);
            }
        }

        return results;
    }

    public ArrayList<Movie> searchByActor(Cinema databaseDb, String actor) {
        ArrayList<Movie> results = new ArrayList<>();

        for (Movie movie : databaseDb.getMovies()) {
            for(Actor a : movie.getActors()){
                if (a.getFullName().contains(actor)) {
                    results.add(movie);
                }
            }
        }

        return results;
    }

    public ArrayList<Movie> searchByKeywords(Cinema databaseDb, String keyWords) {
        ArrayList<Movie> results = new ArrayList<>();

        for (Movie movie : databaseDb.getMovies()) {
            if (movie.getKeyWords().equalsIgnoreCase(keyWords)) {
                results.add(movie);
            }
        }

        return results;
    }
}
