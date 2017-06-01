package KokoFlix;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class finds the matches for the attributes of a Movie and creates the
 * instances of the Movie.
 *
 * @author dennissuarez
 */
public class MatchRegex {

    // Array to store all the movies.
    private static final ArrayList<Movie> allMovieInfo = new ArrayList();

    // Movie attributes.
    private static String movieName;
    private static String movieGenre;
    private static String movieReleaseDate;
    private static String[] movieTimes;
    private static String movieCode;
    private static String movieRunTime;
    private static String movieDescription;
    private static String movieCast;
    private static String movieDirectors;
    private static String movieUrl;

    /**
     * This method clears the allMovieInfo to avoid the overlapping of movies
     * every time a new theater is created.
     */
    public static void flush() {
        allMovieInfo.clear();
    }

    /**
     * The method calls other methods to find all the movies in the given file.
     *
     * @param file
     * @return list of Movies found
     */
    public static ArrayList<Movie> createMovies(String file) {

        // Find the information of the movie.
        makeTheater(file);

        return allMovieInfo;
    }

    /**
     * Cleaning all of genres, director and cast of the movie
     *
     * @param rawData
     * @return listOfGenres
     */
    private static String[] dataListCleaner(String rawData) {

        //Take out any quotations mark left floating.
        String[] data = {rawData.replaceAll("\"", "").
            replaceAll("\\[", "").replaceAll("\\]", "")
            .replaceAll(",", ", ")};
        return data;
    }

    /**
     * Clean up the runTime for the movie
     *
     * @param rawData
     * @return runTime
     */
    private static String runTimeDataCleaner(String rawData) {
        String output = "";
        
        // The pattern is always ddHddM so we convert it to dd:dd.
        if(rawData != null){
            output = rawData.substring(3);
        }
        else{
            output = "N/A";
        }
        return output.replace("H", ":").replace("M", "");
    }

    /**
     * This method creates the movies.
     *
     * @return movie
     */
    private static Movie createMovie() {

        Movie movie = new Movie(movieTimes, movieName,
                dataListCleaner(movieGenre), movieCode,
                runTimeDataCleaner(movieRunTime), movieDescription,
                movieReleaseDate, dataListCleaner(movieCast),
                dataListCleaner(movieDirectors), movieUrl);

        return movie;
    }

    /**
     * This method sets gathers all the information to create a theater.
     *
     * @param file
     */
    public static void makeTheater(String file) {
        try {
            /* Parse the information which is in JSON format
               to find relevant info*/
            JSONParser parser = new JSONParser();
            JSONArray results = (JSONArray) parser.parse(file);
            
            for (int i = 0; i < results.size(); i++) {
                JSONObject obj = (JSONObject) results.get(i);
                
                // Setting up values from the movie info
                setUpVariables(obj, "title", "releaseDate", "genres",
                        "longDescription", "runTime", "topCast",
                        "directors", "officialUrl");
                
                movieDirectors = getArray((JSONArray) obj.get("directors"));
                movieGenre = getArray((JSONArray) obj.get("genres"));
                movieCode = jsonArray(obj, "ratings", "code");
                String showtimes = jsonArray(obj, "showtimes", "dateTime");
                cleanUpShowTimes(showtimes);
                
                // Add to the list of movies for the Theater
                allMovieInfo.add(createMovie());
            }
        } catch (ParseException ex) {
            new Alerts("Error", "Parser Error", "JSONParse",
                    "The parser ran into a problem while parsing the date!");
        }
    }
    
    /**
     * This method gets the movie genres
     * 
     * @param array
     * @return 
     */
    private static String getArray(JSONArray array){
        String genres = "";
        
        if(array != null){
            for(int i = 0; i < array.size(); i++){
                genres += array.get(i) + ",";
            }
        }
        else{
            genres = "N/A  ";
        }
        
        return genres.substring(0, genres.length() - 2);
    }

    /**
     * The method is used to reduce duplicated code to traverse an
     * array in the JSON file.
     *
     * @param obj
     * @param array
     * @param node
     * @return
     */
    private static String jsonArray(JSONObject obj, String array, String node) {

        // Initializing variables
        String result = "";
        JSONArray list = (JSONArray) obj.get(array);

        // Check if the array with the given name exists
        if (list != null) {

            // Traverse the array and get the desired node
            for (int i = 0; i < list.size(); i++) {
                JSONObject nodeObject = (JSONObject) list.get(i);
                result += nodeObject.get(node) + " ";
            }
        } else {
            result = "N/A";
        }
        return result;
    }

    /**
     * The method sets up the variables for the Theater information.
     *
     * @param obj
     * @param title
     * @param releaseDate
     * @param genres
     * @param longDescription
     * @param runTime
     * @param topCast
     * @param directors
     * @param officialUrl
     */
    private static void setUpVariables(JSONObject obj, String title,
        String releaseDate, String genres, String longDescription,
        String runTime, String topCast, String directors, String officialUrl) {

            movieName = (String) obj.get(title);
            movieReleaseDate = (String) obj.get(releaseDate);
            movieDescription = (String) obj.get(longDescription);
            movieRunTime = (String) obj.get(runTime);

            movieCast = "" + obj.get(topCast);
            movieUrl = (String) obj.get(officialUrl);
    }

    /**
     * This helper method is used to clean up the show times for
     * the Movie.
     * 
     * @param showtimes 
     */
    private static void cleanUpShowTimes(String showtimes) {
        String[] times = showtimes.split(" ");
        
        // Traversing each showtime and cleaning it
        for (int u = 0; u < times.length; u++) {
            if (times[u] != null) {
                times[u] = times[u].substring(11);
            }
        }
        movieTimes = times;
    }
}