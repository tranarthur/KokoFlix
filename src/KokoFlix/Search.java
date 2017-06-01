package KokoFlix;

import java.util.ArrayList;

/**
 * The class gives the user the option to search in one theater or all
 * the theaters in the TheaterLocation interface.
 *
 * @author dennissuarez
 */
public class Search implements TheatersLocation{
    
    /**
     * Makes the search of the all the movies in the given Theater.
     * 
     * @param theaterName
     * @return a Theater
     */
    public static Theater searchOneTheater(String theaterName){
        
        ArrayList<Movie> reset = new ArrayList();
        String file = getFile(theaterName);

        // Storage of the file input
        
        reset.addAll(MatchRegex.createMovies(file));
        
        // Get the theater from the regex match
        Theater theater = new Theater(reset, theaterName);
        
        MatchRegex.flush();
        
        return theater;
    }
    
    /**
     * The method searches all the movies in all the theaters that have
     * been defined in the TheatersLocation interface.
     * 
     * @param movieName
     * @return a list of Theaters
     */
    public static ArrayList<Theater> searchAllTheaters(String movieName){
        ArrayList<Theater> theaterList = new ArrayList();
        
        // Traverse all the theaters in the interface TheatersLocation
        for(int i = 0; i < TheatersLocation.ALL_THEATERS.length; i++){
            
            // To avoid repetition call the method that return the theater.
            theaterList.add(searchOneTheater(
                    TheatersLocation.ALL_THEATERS[i]));
        }
        return theaterList;
    }
    
    /**
     * This method handles which theater has the user picked and
     * return the URL.
     * 
     * @param theatreName
     * @return theaterURL
     */
    private static String getLocation(String theatreName){
        String theaterURL = "";
        
        switch(theatreName.toUpperCase()){
            case "WINSTON_PARK_CINEMAS": 
                theaterURL = WINSTON_PARK_CINEMAS;break;
            case "MISSISSAUGA_CINEMAS": 
                theaterURL = MISSISSAUGA_CINEMAS;break;
            case "COURTNEY_PARK_CINEMAS": 
                theaterURL = COURTNEY_PARK_CINEMAS;break;
            case "MILTON_CINEMAS": 
                theaterURL = MILTON_CINEMAS;break;
            case "ORION_GATE_CINEMAS": 
                theaterURL = ORION_GATE_CINEMAS;break;
            case "OAKVILLE_VIP_CINEMAS": 
                theaterURL = OAKVILLE_VIP_CINEMAS;break;
            case "QUEENSWAY_VIP_CINEMAS": 
                theaterURL = QUEENSWAY_VIP_CINEMAS;break;
            case "BURLINGTON_CINEMAS": 
                theaterURL = BURLINGTON_CINEMAS;break;
            case "BRAMPTON_CINEMAS": 
                theaterURL = BRAMPTON_CINEMAS;break;
        }
        
        return theaterURL;
    }
    
    /**
     * If the file for that Theater does not exit this method
     * creates on for it by calling other classes and methods.
     * 
     * @param theaterName 
     */
    private static String getFile(String theaterName){
        
        // Get the URL for the gien theater.
        String theatreURL = Search.getLocation(theaterName);
        
        /* Created connection does not need to be saved
          since it saves the data into a file in the computer*/ 
        ApiConnectionToFile f = new ApiConnectionToFile(theatreURL,theaterName);
        
        return f.getRawData().toString();
    }
}