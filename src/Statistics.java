import java.util.ArrayList;
import java.util.Date;

public class Statistics {

    private Date playDate;
    private ArrayList<Movie> movies;

    public Statistics (){
        this.playDate = playDate;
        this.movies = new ArrayList<>();
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setPlayDate(Date playDate) {
        this.playDate = playDate;
    }

    public Date getPlayDate() {
        return playDate;
    }

    @Override
    public String toString() {
        return "Statistics " +
                "Viewed on: " + playDate +
                ", movie: " + movies;
    }

}
