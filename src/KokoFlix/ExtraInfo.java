package KokoFlix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class gets extra information about a given movie.
 *
 * @author khoatran
 */
public class ExtraInfo {
    
    /**
     * This method is used to get the poster url, background url
     * and trailer url of a movie
     * 
     * 
     * @param movieTitle the title of the movie spaces must be replaced by +
     * @return String[] a string array of movie information
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException 
     */
    static String[] GetMovieInfo(String movieTitle)
            throws MalformedURLException, IOException, ParseException {

        //holds the movie information urls
        String[] movieDataList = new String[3];

        //create the url to read from
        URL url;
        url = new URL("https://api.themoviedb.org/3/search/movie?api_"
                + "key=44ea620364c47750d77fce1862a10ec9&query=" + movieTitle);
        URLConnection con = url.openConnection();
        
        //read from the url and add to a string builder
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuilder movieData = new StringBuilder();
        while ((line = br.readLine()) != null) {
            movieData.append(line);
        }
        
        //parse the information which is in JSON format to find relevant info
        JSONParser parser = new JSONParser(); 
        JSONObject root;
        root = (JSONObject) parser.parse(movieData.toString());
        JSONArray results = (JSONArray) root.get("results");
        JSONObject obj = (JSONObject) results.get(0);
        String posterUrl = (String) obj.get("poster_path");
        String backdropUrl = (String) obj.get("backdrop_path");
        String id = (obj.get("id")).toString();

        //store the information into the array
        movieDataList[0] = posterUrl;
        movieDataList[1] = backdropUrl;
        
        
        // this process is repeated once again because the trailer
        // is on a different url but also required information which
        // was acquired in the previous step
        url = new URL("https://api.themoviedb.org/3/movie/" + id 
                + "/videos?api_key=44ea620364c47750d77fce1862a10"
                + "ec9&language=en-US");
        
        con = url.openConnection();
        is = con.getInputStream();
        BufferedReader br2 = new BufferedReader(new InputStreamReader(is));
        String line2 = null;
        StringBuilder trailerData = new StringBuilder();
        while ((line = br2.readLine()) != null) {
            trailerData.append(line);
        }

        root = (JSONObject) parser.parse(trailerData.toString());
        results = (JSONArray) root.get("results");
        for (int i = 0; i < results.size(); i++) {
            obj = (JSONObject) results.get(i);
            String trailerUrl = (String) obj.get("key");
            String type = (String) obj.get("type");
            movieDataList[2] = trailerUrl;
            if (type.equals("Trailer"))
                break;            
        };

        return movieDataList;
    }
}
