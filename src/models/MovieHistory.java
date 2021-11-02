package models;

import java.io.Serializable;
import java.util.Date;

public class MovieHistory implements Serializable {

    private Movie movie;
    private Date datePlayed;

    public MovieHistory(Movie movie, Date datePlayed, User userPlaying) {
        this.movie = movie;
        this.datePlayed = datePlayed;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(Date datePlayed) {
        this.datePlayed = datePlayed;
    }

    public String toString() {
        String var10000 = this.movie.getTitle();
        return "Title: " + var10000 + " was played on " + this.datePlayed;
    }
}
