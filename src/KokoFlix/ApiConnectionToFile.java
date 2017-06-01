package KokoFlix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * This class connects to the Api to search for theater information.
 *
 * @author dennissuarez
 */
public class ApiConnectionToFile {
    
    private StringBuilder data = new StringBuilder();

    /**
     * The method connects to a website and takes all the information
     * and saves it into a StringBuilder.
     * 
     * @param urlString
     * @param theatreName 
     */
    public ApiConnectionToFile(String urlString, String theatreName) {

        try {
            // Creating the URL
            String nextLine;
            
            //Could be malformed but we are sure it's good.
            URL url = new URL(urlString);

            // Open connection to given url
            URLConnection urlConnection = url.openConnection();

            // create BufferedReader to buffer the given url's HTML source
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream())
            );

            // Read each line of HTML code and store it in a StringBuilder
            while ((nextLine = reader.readLine()) != null) {
                data.append(nextLine);
            }
            
        } catch (IOException e) {
            new Alerts("Error", "URL Error", "No Internet Connection",
                    "Please connect to the Internet!");
        }
    }
    
    /**
     * The method returns all the theater information.
     * 
     * @return data
     */
    public StringBuilder getRawData(){
        return data;
    }
}
