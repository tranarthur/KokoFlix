package KokoFlix;

/**
 * This class tries to model a Movie according to the data
 * that will be displayed in the App.
 *
 * @author dennissuarez
 */
public class Movie implements Comparable<Movie>{
        
    private final String[] TIMES;
    private final String MOVIE_NAME;
    private final String[] GENRE;
    private final String RATE;
    private final String RUNTIME;
    private final String DESCRIPTION;
    private final String RELEASE_DATE;
    private final String[] CAST;
    private final String[] DIRECTORS;
    private final String OFFICIAL_SITE;

    /**
     * This is a constructor to create a Movie.
     * 
     * @param TIMES
     * @param MOVIE_NAME
     * @param GENRE
     * @param RATE
     * @param RUNTIME
     * @param DESCRIPTION
     * @param RELEASE_DATE
     * @param CAST
     * @param DIRECTORS
     * @param OFFICIAL_SITE 
     */
    public Movie(String[] TIMES, String MOVIE_NAME, String[] GENRE,
            String RATE, String RUNTIME, String DESCRIPTION, String RELEASE_DATE
            , String[] CAST, String[] DIRECTORS, String OFFICIAL_SITE) {
        
        this.TIMES = TIMES;
        this.MOVIE_NAME = MOVIE_NAME;
        this.GENRE = GENRE;
        this.RATE = RATE;
        this.RUNTIME = RUNTIME;
        this.DESCRIPTION = DESCRIPTION;
        this.RELEASE_DATE = RELEASE_DATE;
        this.CAST = CAST;
        this.DIRECTORS = DIRECTORS;
        this.OFFICIAL_SITE = OFFICIAL_SITE;
    }    

    /**
     * Getter to get the list of genres of the movie..
     * 
     * @return genres
     */
    public String getGenre() {
        String genres = "";
        for(String genr:GENRE){
            genres+= genr + " ";
        }
        return genres;
    }

    /**
     * Getter to get the times the movie is playing.
     * 
     * @return TIMES
     */
    public String getTIMES() {
        String result = "";
        for(int i = 0; i < TIMES.length; i++){
            result += TIMES[i] + " ";
        }
        return result;
    }

    /**Getter to get the name of the movie.
     * 
     * @return MOVIE_NAME
     */
    public String getMOVIE_NAME() {
        return MOVIE_NAME;
    }

    /**
     * Getter to get the rate of the movie.
     * 
     * @return RATE
     */
    public String getRATE() {
        return RATE;
    }

    /**
     * Getter to get the runtime of the movie.
     * 
     * @return RUNTIME
     */
    public String getRUNTIME() {
        return RUNTIME;
    }

    /**
     * Getter to get the description of the movie.
     * 
     * @return DESCRIPTION
     */
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    /**
     * Getter to get the release date of the movie.
     * 
     * @return RELEASE_DATE
     */
    public String getRELEASE_DATE() {
        return RELEASE_DATE;
    }

    /**
     * Getter to get the cast of the movie.
     * 
     * @return CAST
     */
    public String getCAST() {
        String cast = "";
        for(String genr:CAST){
           cast+= genr + " ";
        }
        return cast;
    }

    /**
     * Getter to get the directors of the movie.
     * 
     * @return DIRECTORS
     */
    public String getDIRECTORS() {
        String directors = "";
        for(String genr:DIRECTORS){
            directors+= genr + " ";
        }
        return directors;
    }

    /**
     * Getter to get the official website of the movie.
     * 
     * @return OFFICIAL_SITE
     */
    public String getOFFICIAL_SITE() {
        return OFFICIAL_SITE;
    }
    
    /**
     * This method is used to compare between movies and make an order.
     * 
     * @param movie
     * @return 
     */
    @Override
    public int compareTo(Movie movie) {
        return this.getMOVIE_NAME().compareTo(movie.getMOVIE_NAME());
    }

    /**
     * This method returns a string representation of the instance
     * of this Movie class.
     * 
     * @return 
     */
    @Override
    public String toString() {
        return MOVIE_NAME;
    }

}
