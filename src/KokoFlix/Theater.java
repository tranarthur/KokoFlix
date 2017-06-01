package KokoFlix;

import java.util.ArrayList;

/**
 * This class mimics the attributes of a theater.
 *
 * @author dennissuarez
 */
public class Theater implements Comparable<Theater>{
    private ArrayList<Movie> movieList;
    private final String THEATER_NAME;
    
    /**
     * The constructor of a Theater
     * 
     * @param movieList list of movies playing
     * @param THEATER_NAME name of the theater
     */
    public Theater(ArrayList<Movie> movieList, String THEATER_NAME) {
        this.movieList = movieList;
        this.THEATER_NAME = THEATER_NAME;
    }

    /**
     * The method returns the list of movies played in this theater.
     * 
     * @return a list of movies
     */
    public ArrayList<Movie> getListOfMovies() {
        return movieList;
    }
    
    /**
     * The method returns the name this theater.
     * 
     * @return the theater names
     */
    public String getTheaterName() {
        return THEATER_NAME;
    }
    
    /**
     * Sets the list of movies to the given Movie
     * 
     * @param movieList
     */
    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }
    
    /**
     * To give Theaters an order
     * 
     * @param theater
     * @return an integer to represent if it is a higher or lower value.
     */
    @Override
    public int compareTo(Theater theater) {
        return this.getTheaterName().compareTo(theater.getTheaterName());
    }

    /**
     * A representation of the instance of this class.
     * 
     * @return Theater name
     */
    @Override
    public String toString() {
        return THEATER_NAME;
    }
}
