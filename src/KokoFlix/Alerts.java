package KokoFlix;

import javafx.scene.control.Alert;

/**
 * This class creates the alerts raised by this project.
 *
 * @author dennissuarez
 */
public class Alerts {
    
    /**
     * The constructor makes the costume alerts for errors or warnings. The
     * default alert will be an error alert if the alert type does not match
     * any of the cases.
     *
     * @param alertType
     * @param title
     * @param header
     * @param content
     */
    public Alerts (String alertType, String title, String header,
            String content) {
    
        Alert alert;
        
        //Check the type of Alert
        switch (alertType) {
            case "Error":
                alert = new Alert(Alert.AlertType.ERROR);break;
            case "Warning":
                alert = new Alert(Alert.AlertType.WARNING);break;
            default:
                alert = new Alert(Alert.AlertType.ERROR);break;
        }
        
        //Set up the alert as stated
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
