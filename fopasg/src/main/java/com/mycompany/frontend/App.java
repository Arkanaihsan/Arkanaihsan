package com.mycompany.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Stack;
import com.mycompany.frontend.helper.MessageController;
import com.mycompany.frontend.helper.PopUpBoxController;

/***
 * THIS APP CLASS IS SERVED AS THE ***ENTRY POINT*** OF THE ENTIRE APPLICATION.
 * - RUN THIS FILE TO START THE APP.
 * 
 ***/
public class App extends Application {

    /***
     * STATIC VARIABLES DECLARATION.
     * 
     ***/
    private static Stack<Scene> sceneHistory = new Stack<>(); // This is used to keep track the previous scene (But it
                                                              // not used for now).
    private static Stage stage; // This is used to refer to the main stage with the same reference, which used
                                // throughout the App.

    /***
     * MAIN METHOD TO START.
     * 
     ***/
    public static void main(String[] args) {
        launch();
    }

    /***
     * INITIAL SETUP.
     * 
     ***/
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Scene initialScene = new Scene(loadFXML("main-menu").getRoot(), 900, 600); // Landing page here.
        stage.setScene(initialScene);
        stage.setTitle("Digital Diary");
        stage.show();
        sceneHistory.push(initialScene); // Push the initial scene to the history stack to enable scene navigation
                                         // later.
    }

    /***
     * METHOD TO LOAD THE FXML FILE.
     * 
     ***/
    private static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.load();
        return fxmlLoader;
    }

    /***
     * METHOD TO SHOW A CONFIRMATION POP UP.
     * 
     ***/
    public static void openConfirmationPopUp(String confirmationText, String successMessage, String failedMessage)
            throws IOException {
        FXMLLoader loader = loadFXML("pop-up-box");
        Parent root = loader.getRoot();
        PopUpBoxController controller = loader.getController(); // Get the PopUpBoxController.
        controller.setConfirmationText(confirmationText); // Use the controller to set the confirmation text that
                                                          // displayed to users.
        controller.setSuccessMessageText(successMessage); // Use the controller to set the success message that
                                                          // displayed to the users when user wanted action performed
                                                          // correctly.
        controller.setFailedMessageText(failedMessage); // Use the controller to set the failed message that displayed
                                                        // to the users when user wanted action performed wrongly.

        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED); // Remove the default window decorations (title bar, close,
                                                      // minimize, and maximize buttons).
        popupStage.initModality(Modality.APPLICATION_MODAL);// Set the modality of the pop-up to APPLICATION_MODAL,
                                                            // making it block interactions with other application
                                                            // windows until the pop-up is closed.
        Scene scene = new Scene(root);
        popupStage.setScene(scene);

        // Get the dimensions of the parent stage and screen
        double stageX = stage.getX();
        double stageY = stage.getY();
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();

        // Calculate the center position
        double popupWidth = root.prefWidth(-1);
        double popupHeight = root.prefHeight(-1);
        double centerX = stageX + (stageWidth - popupWidth) / 2;
        double centerY = stageY + (stageHeight - popupHeight) / 2;

        // Set the position of the pop-up
        popupStage.setX(centerX);
        popupStage.setY(centerY);

        popupStage.showAndWait(); // Display the pop-up window and block further code execution until the pop-up
                                  // is closed.
    }

    /***
     * METHOD TO SHOW A POP UP AT CENTER.
     * 
     ***/
    public static void openPopUp(String filename) throws IOException {
        Parent root = loadFXML(filename).getRoot();
        Stage popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED); // Remove the default window decorations (title bar, close,
                                                      // minimize, and maximize buttons).
        popupStage.initModality(Modality.APPLICATION_MODAL); // Set the modality of the pop-up to APPLICATION_MODAL,
                                                             // making it block interactions with other application
                                                             // windows until the pop-up is closed.
        Scene scene = new Scene(root);
        popupStage.setScene(scene);

        // Get the dimensions of the parent stage and screen
        double stageX = stage.getX();
        double stageY = stage.getY();
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();

        // Calculate the center position
        double popupWidth = root.prefWidth(-1);
        double popupHeight = root.prefHeight(-1);
        double centerX = stageX + (stageWidth - popupWidth) / 2;
        double centerY = stageY + (stageHeight - popupHeight) / 2;

        // Set the position of the pop-up
        popupStage.setX(centerX);
        popupStage.setY(centerY);

        popupStage.showAndWait(); // Display the pop-up window and block further code execution until the pop-up
                                  // is closed.
    }

    /***
     * METHOD TO SHOW A POP UP AT TOP.
     * 
     ***/
    public static void openPopUpAtTop(String filename, String message) throws IOException {
        FXMLLoader loader = loadFXML(filename);
        Parent root = loader.getRoot();
        MessageController controller = loader.getController(); 
        controller.setMessageText(message); 

        Stage messageBoxStage = new Stage();
        messageBoxStage.initStyle(StageStyle.UNDECORATED); 
        Scene scene = new Scene(root);
        messageBoxStage.setScene(scene);

        // Get the dimensions of the parent stage and screen
        double stageX = stage.getX();
        double stageY = stage.getY();
        double stageWidth = stage.getWidth();

        // Calculate the center position
        double popupWidth = root.prefWidth(-1);
        double centerX = stageX + (stageWidth - popupWidth) / 2;
        double higherY = stageY + 80;

        // Set the position of the pop-up
        messageBoxStage.setX(centerX);
        messageBoxStage.setY(higherY);

        messageBoxStage.show();

        // After 3 seconds, close the pop-up
        javafx.animation.PauseTransition pause = new
        javafx.animation.PauseTransition(
        javafx.util.Duration.seconds(3));
        pause.setOnFinished(e -> messageBoxStage.close());
        pause.play();
    }

    /*** Below two are not used for now ***/
    // Method to switch between scenes
    public static void switchScene(String fxml) throws IOException {
        Scene newScene = new Scene(loadFXML(fxml).getRoot()); // Create a new scene
        stage.setScene(newScene); // Set the new scene into current stage
        stage.show();
        sceneHistory.push(newScene); // Push the new scene to the history stack
    }

    // Method to go back to the previous scene
    public static void goBackToPreviousScene() {
        if (sceneHistory.size() > 1) { // If got previous scene
            sceneHistory.pop(); // Remove the current scene
            Scene previousScene = sceneHistory.peek(); // Get the previous scene
            stage.setScene(previousScene); // Show the previous scene
            stage.show();
        }
    }
}
