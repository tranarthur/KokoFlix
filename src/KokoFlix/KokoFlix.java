package KokoFlix;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * This is the main class the runs the application.
 * 
 * @author dennissuarez & khoatran
 */
public class KokoFlix extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the Application in the login scene
     * 
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // Set up the first Scene.
        Image background = new Image("Movie_Theater.jpg");
        StackPane pane = new StackPane();
        Button login_btn = new Button("Start App");
        pane.getChildren().addAll(new ImageView(background), login_btn);
        Scene login = new Scene(pane, 1550, 720); 
        
        // Show the login.
        primaryStage.setScene(login);
        primaryStage.setTitle("KokoFlix");
        primaryStage.show();
        
        login_btn.setOnAction(e->{
            ApplicationUI.run(primaryStage);
        });
    }
}
