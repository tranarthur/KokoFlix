package KokoFlix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * The UI for the application
 * 
 * @author khoatran
 */
public class ApplicationUI {

    //Global Variables hold movie information
    static Label movieTitle;
    static Label movieRating;
    static Label releaseDate;
    static Label movieDuration;
    static Label genre;
    static Label actors;
    static Label director;
    static Label moreInfo;
    static Label showTimeTitle;
    static Label theaterName;
    static Label date;
    static Label showTimes;
    static Label descriptionTitle;
    static Label description;
    static WebView trailerVideo;
    static Theater currentTheater;
    static Movie currentMovie;
    static String url;
    static BackgroundImage myBI;
    static Pane backgroundImage;
    static ImageView posterFrame;
    static ArrayList<Movie> movies;
    
    static boolean check = true;
    static Pane pane;
    

    /**
     * Creates the Pane which displays all the movie information
     *
     * @return Pane The pane that holds the movie information
     */
    static Pane displayMovieInfo() {
        
        // The main pane that holds all the movie information
        StackPane movieInfoContainer = new StackPane();
        
        // The content HBox holds the two halves:
        // leftSide which contains movie title, poster, and movie information
        // rightSide half which contains the trailer and description
        HBox content = new HBox();
        content.setId("content");
        content.setPrefWidth(1280);
        content.setPadding(new Insets(20));
        
        VBox leftSide = new VBox(10);
        leftSide.setPrefWidth(700);
        leftSide.setId("leftSide");
        leftSide.setPadding(new Insets(0, 10, 0, 0));
        
        VBox rightSide = new VBox(50);
        rightSide.setPadding(new Insets(40, 40, 0, 40));
        rightSide.setId("rightSide");
        rightSide.setPrefWidth(580);
        rightSide.setMaxWidth(580);
        
        
        //gradient is used to place a grey filter on the background image 
        Pane gradient = new Pane();
        gradient.setPrefSize(720, 1280);
        gradient.setId("gradient");
        backgroundImage = new Pane();
        

        //leftSplit is used to split the poster and movie information
        HBox leftSplit = new HBox(20);
        leftSplit.setPadding(new Insets(30, 0, 0, 20));
        
        
        //title is used to hold movie title
        StackPane title = new StackPane();
        movieTitle = new Label();
        movieTitle.setStyle("-fx-font-family:"
                + " 'Merriweather Sans'; -fx-font-size: 55;");
        title.setPrefHeight(100);
        title.setId("title");
        title.getChildren().add(movieTitle);
        
        
        //The movie poster
        posterFrame = new ImageView();
        posterFrame.setFitWidth(340);
        posterFrame.setFitHeight(510);
        

        //Movie information container
        VBox infoPane = new VBox(20);
        infoPane.setPrefWidth(260);
        infoPane.setPadding(new Insets(20));
        infoPane.setId("infoPane");
        VBox movieInfo = new VBox(8);
        

        movieRating = new Label();
        movieRating.setStyle("-fx-font-size: 30;");
        releaseDate = new Label();
        movieDuration = new Label();
        genre = new Label();
        genre.setWrapText(true);
        actors = new Label();
        actors.setWrapText(true);
        director = new Label();
        moreInfo = new Label();
        moreInfo.setWrapText(true);
        
        
        movieInfo.getChildren().addAll(movieRating, releaseDate, movieDuration,
                genre, director, actors, moreInfo);
        
                
        VBox showtimePane = new VBox(8);
        showTimeTitle = new Label("SHOWTIMES");
        showTimeTitle.setStyle(
                "-fx-font-size: 20;");
        theaterName = new Label();
        date = new Label(getDate());
        showTimes = new Label();
        showTimes.setWrapText(true);
        showtimePane.getChildren().addAll(
                showTimeTitle, theaterName, date, showTimes);

        
        infoPane.getChildren().addAll(movieInfo, showtimePane);

        
        trailerVideo = new WebView();
        trailerVideo.setMaxSize(480, 270);

        
        //The Description Container
        VBox descriptionBox = new VBox(20);
        descriptionBox.setPadding(new Insets(20));
        descriptionBox.setId("descriptionBox");
        descriptionBox.setAlignment(Pos.CENTER);
        descriptionTitle = new Label("Description");
        descriptionTitle.setStyle(
                "-fx-font-family: Poppins; -fx-font-size: 20;");
        description = new Label();
        description.setStyle(
                "-fx-font-family: Roboto; -fx-font-size: 16;"
                + "-fx-line-spacing: 0.25em;");
        description.setWrapText(true);
        descriptionBox.getChildren().addAll(descriptionTitle, description);

        
        //Putting everything together
        leftSplit.getChildren().addAll(posterFrame, infoPane);
        leftSide.getChildren().addAll(title, leftSplit);
        rightSide.getChildren().addAll(trailerVideo, descriptionBox);
        content.getChildren().addAll(leftSide, rightSide);
        movieInfoContainer.getChildren().addAll(
                backgroundImage, gradient, content);
        
        return movieInfoContainer;
    }
    
    
    /**
     * 
     * The pane that displays the sidebar which contains the logo,
     * theaters list and movies list
     * 
     * @return Pane The pane that displays the sidebar information
     */
    static Pane displaySideBar() {
        
        //Container is used to hold the entire sidebar
        VBox container = new VBox(15);
        container.setMaxHeight(720);
        container.setPrefWidth(300);
        container.setId("sideBar");
        container.setPadding(new Insets(10));
        
        
        //Holds the logo of the application
        StackPane logoBox = new StackPane();
        logoBox.setPadding(new Insets(5));
        logoBox.setId("logoBox");
        Label logo = new Label("KOKOFLIX");
        logo.setStyle("-fx-font-family: Raleway; -fx-font-size: 40;");
        logoBox.getChildren().add(logo);
        

        //Holds the theater listview and title
        VBox theaterInfo = new VBox();
        StackPane theaterBox = new StackPane();
        theaterBox.setPadding(new Insets(5));
        Label theaterLabel = new Label("Theater");
        theaterLabel.setStyle(
                "-fx-font-family: Poppins;");
        theaterLabel.setFont(new Font(20));
        theaterBox.setId("theaterBox");
        theaterBox.getChildren().add(theaterLabel);
        
        
        //Holds the movie listview and title
        VBox movieInfo = new VBox();
        movieInfo.setId("movieInfo");
        StackPane movieBox = new StackPane();
        movieBox.setPadding(new Insets(5));
        Label movieLabel = new Label("Movies");
        movieLabel.setStyle(
                "-fx-font-family: Poppins;");
        movieLabel.setFont(new Font(20));
        movieBox.setId("movieBox");
        movieBox.getChildren().add(movieLabel);

        
        //Theater ListView
        ListView<Theater> theaterList = new ListView();
        ArrayList<Theater> list = getAllTheaters("Saban's Power Rangers");
        theaterList.getItems().addAll(list);
        Theater theaterChoice;

        
        //Movie ListView
        ListView<Movie> movieList = new ListView();

        
        //when the theater is changed
        theaterList.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends Theater> observable,
                        Theater oldTheater, Theater chosenTheater) -> {
                    currentTheater = chosenTheater;

                    /* if no theater was picked previously
                       get the list of movies from the theater
                       and put them into the movie listview*/
                    if (oldTheater == null) {
                        
                        movies = chosenTheater.getListOfMovies();
                        Collections.sort(movies);
                        movieList.getItems().setAll(movies);
                    }
                    
                    
                    /*if there was a previous theater and movie chosen
                    clear the pane that holds all the movie information
                    get the movies from the selected theater
                    load the default background and set current movie to null*/
                    else if (oldTheater != null && currentMovie != null) {

                        pane.getChildren().clear();
                        movies = chosenTheater.getListOfMovies();
                        Collections.sort(movies);
                        movieList.getItems().setAll(movies);
                        trailerVideo.getEngine().loadContent("");
                        myBI = new BackgroundImage(
                            new Image("Movie_Theater.jpg", 1280, 720, false, true),
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT);
                        
                        pane.setBackground(new Background(myBI));
                        currentMovie = null;
                    }
                    
                    
                    /* this condition is true if the user switches theaters
                       without choosing a movie previously
                       gets the list movies and put into the movie listview*/
                    else if (currentMovie == null) {
                        movies = chosenTheater.getListOfMovies();
                        Collections.sort(movies);
                        movieList.getItems().setAll(movies);
                    }
                });

        
        //when the movie is changed
        movieList.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends Movie> observable,
                        Movie oldMovie, Movie chosenMovie) -> {
                    
                    /* if a movie was previously chosen and
                       a new movie was selected*/
                    if (oldMovie != null && chosenMovie != null) {
                        
                        pane.getChildren().clear();
                        trailerVideo.getEngine().loadContent("");
                        pane.getChildren().add(displayMovieInfo());
                        currentMovie = chosenMovie;
                        updateMovieInfo(chosenMovie);
                        
                    // if it the first time the user is choosing a movie     
                    } else if(chosenMovie != null) {
                        pane.getChildren().clear();
                        currentMovie = chosenMovie;
                        pane.getChildren().add(displayMovieInfo());
                        currentMovie = chosenMovie;
                        updateMovieInfo(chosenMovie);

                    } 
                    // if the user switches theaters
                    else {
                        chosenMovie = null;
                    }

                });
        
        //putting it all together
        theaterInfo.getChildren().addAll(theaterBox, theaterList);
        movieInfo.getChildren().addAll(movieBox, movieList);
        container.getChildren().addAll(logoBox, theaterInfo, movieInfo);
        
        return container;

    }
    
    /**
     * Updates the movie information
     * 
     * @param movie the movie to update the information with 
     */
    static void updateMovieInfo(Movie movie) {

        //used to search for the movie: replaces spaces with the plus sign
        String movieString = (movie.toString()).replaceAll(" ", "+");

        //removes '3D' if the title contains it
        if (movieString.contains("3D")) {
            movieString = movieString.substring(0, movieString.length() - 3);
        }

        /* tries to search the movie and get the background image,
           poster and video trailer information*/
        try {
        String[] extraMovieInfo = ExtraInfo.GetMovieInfo(movieString);

        myBI = new BackgroundImage(
                    new Image(
                        "http://image.tmdb.org/t/p/original" + extraMovieInfo[1]
                        ,1280, 720, false, true),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT);

        backgroundImage.setBackground(new Background(myBI));

        posterFrame.setImage(new Image("http://image.tmdb.org/t/p/original"
                + extraMovieInfo[0]));

        trailerVideo.getEngine().load(
                "https://www.youtube.com/embed/" + extraMovieInfo[2]
                        + "?rel=0&autoplay=1"
        );
        } catch (Exception e) {
            trailerVideo.getEngine().load("");
        }

        //change the labels with the appropriate information for the movie
        movieTitle.setText(movie.getMOVIE_NAME());
        movieRating.setText(movie.getRATE());
        releaseDate.setText("Release Date:\n" + movie.getRELEASE_DATE());
        movieDuration.setText("Runtime: " + movie.getRUNTIME());
        genre.setText("Genre:\n" + movie.getGenre());
        actors.setText("Cast:\n" + movie.getCAST());
        director.setText("Director:\n" + movie.getDIRECTORS());
        moreInfo.setText(movie.getOFFICIAL_SITE());
        theaterName.setText(currentTheater.toString());
        description.setText(movie.getDESCRIPTION());
        showTimes.setText((movie.getTIMES()));

    }
    
    /**
     * Sets the stage for the application
     * 
     * @param stage the stage to display the application
     */
    static void run(Stage stage) {

        HBox appWindow = new HBox();
        appWindow.setId("app");
        pane = new Pane();
        myBI = new BackgroundImage(
                new Image("Movie_Theater.jpg", 1270, 750, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));
        pane.setPrefWidth(1280);
        pane.setPrefHeight(720);

        appWindow.getChildren().addAll(
                ApplicationUI.displaySideBar(),
                pane
        );
        
        // Setting the scene
        Scene scene = new Scene(appWindow, 1580, 920);
        scene.getStylesheets().add("resources/css/main.css");
        scene.getStylesheets()
                .add("https://fonts.googleapis.com/css?family=Raleway");
        scene.getStylesheets()
                .add("https://fonts.googleapis.com/css?family=Roboto");
        scene.getStylesheets()
                .add("https://fonts.googleapis.com/css?family=Merriweather"
                + "+Sans");
        scene.getStylesheets()
                .add("https://fonts.googleapis.com/css?family=Poppins");
        stage.setMaxHeight(720);
        stage.setScene(scene);
        stage.setTitle("KOKOFLIX");
        stage.minWidthProperty().bind(scene.heightProperty().multiply(2));
        stage.minHeightProperty().bind(scene.widthProperty().divide(2));
        stage.show();
    }
    
    /**
     * Gets todays date formatted
     * 
     * @return String Todays date formatted
     */
    static String getDate() {
        Date dNow = new Date();
        String format = "%tA %<tB %<te, %<tY";

        return String.format(format, dNow);
    }
    
    /**
     * Finds the list of all the theaters and all of its movies.
     * 
     * @param movieName the name of the movie
     * @return listOfTheaters
     */
    private static ArrayList<Theater> getAllTheaters(String movieName) {
        return Search.searchAllTheaters(movieName);
    }

}