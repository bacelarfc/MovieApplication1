package services;

import models.Cinema;
import models.Movie;
import models.MovieHistory;
import models.User;

import java.util.List;

public class UserService {

    public User findUserByName(Cinema cinemaDb, String userName) {
        for (User user : cinemaDb.getUsers()) {
            if (user.getName().equalsIgnoreCase(userName)) {
                return user;
            }
        }

        return null;
    }

    public Cinema addMovieToFavourite(Cinema cinemaDb, User activeUser, Movie movie) {
        User user = this.findUserByName(cinemaDb, activeUser.getName());

        if (user != null) {
            user.getFavMovieList().add(movie);
        }

        return cinemaDb;
    }
}
