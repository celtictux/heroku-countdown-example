import com.heroku.sdk.jdbc.DatabaseUrl;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

/**
 * Created by Adam Kelso on 25/09/2016.
 */
public class Main {

    public static int SECONDS_IN_A_DAY = 24 * 60 * 60;
    public static void main(String[] args) {

        port(8080);
        staticFileLocation("/public");

        get("/countdown", (req, res) -> getCountdown());

        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");
            attributes.put("countdown", getCountdown());

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

        get("/db", (req, res) -> {
            Connection connection = null;
            Map<String, Object> attributes = new HashMap<>();
            try {
                connection = DatabaseUrl.extract().getConnection();

                Statement stmt = connection.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
                stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
                ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

                ArrayList<String> output = new ArrayList<String>();
                while (rs.next()) {
                    output.add( "Read from DB: " + rs.getTimestamp("tick"));
                }

                attributes.put("results", output);
                return new ModelAndView(attributes, "db.ftl");
            } catch (Exception e) {
                attributes.put("message", "There was an error: " + e);
                return new ModelAndView(attributes, "error.ftl");
            } finally {
                if (connection != null) try{connection.close();} catch(SQLException e){}
            }
        }, new FreeMarkerEngine());

    }

    public static String getCountdown() {

        Calendar thatDay = Calendar.getInstance();
        thatDay.setTime(new Date(0)); /* reset */
        thatDay.set(Calendar.DAY_OF_MONTH,1);
        thatDay.set(Calendar.MONTH,0); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, 2014);

        Calendar today = Calendar.getInstance();
        long diff =  thatDay.getTimeInMillis() - today.getTimeInMillis();
        long diffSec = diff / 1000;

        long days = diffSec / SECONDS_IN_A_DAY;
        long secondsDay = diffSec % SECONDS_IN_A_DAY;
        long seconds = secondsDay % 60;
        long minutes = (secondsDay / 60) % 60;
        long hours = (secondsDay / 3600); // % 24 not needed

        final String response = new String(days + " days, " + hours + " hours, " + minutes + " minutes and " + seconds + " seconds");

        return response;
    }

}
