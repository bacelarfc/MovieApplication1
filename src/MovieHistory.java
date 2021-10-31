import java.util.Date;

public class MovieHistory {

    private Movie movie;
    private Date datePlayed;
    private User userPlaying;

    public MovieHistory(Movie movie, Date datePlayed, User userPlaying) {
        this.movie = movie;
        this.datePlayed = datePlayed;
        this.userPlaying = userPlaying;
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

    public User getUserPlaying(User activeUser) {
        return userPlaying;
    }

    public void setUserPlaying(User userPlaying) {
        this.userPlaying = userPlaying;
    }
}
