import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Statistics {

 private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
 private ArrayList<Movie> movieArrayList = new ArrayList<>();

 public Statistics(){

 }

 public static void saveDate(){

 }

    public static void main(String[] args) {
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }

}
