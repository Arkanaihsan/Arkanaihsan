package com.mycompany.frontend;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/***
 * THIS CONTROLLER CLASS IS USED FOR mood-indicator.fxml
 * 
 ***/

public class MoodIndicatorController {

    /***
     * ELEMENTS WITH FX:ID
     * 
     ***/
    @FXML
    private Button happyBtn;

    @FXML
    private Button normalBtn;

    @FXML
    private Button sadBtn;

    /***
     * INITILIZATION OF THE CONTROLLER
     * 
     ***/
    @FXML
    public void initialize() {
        // Handle the 'Happy' button click
        happyBtn.setOnAction(event -> {
            // Close the pop-up
            Stage stage = (Stage) happyBtn.getScene().getWindow();
            stage.close();
            // Display a motivational quote
            Platform.runLater(() -> {
                try {
                    App.openPopUp("motivational-quotes");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

        });

        // Handle the 'Normal' button click
        normalBtn.setOnAction(event -> {
            // Close the pop-up
            Stage stage = (Stage) normalBtn.getScene().getWindow();
            stage.close();
            // Display a motivational quote
            Platform.runLater(() -> {
                try {
                    App.openPopUp("motivational-quotes");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        });

        // Handle the 'Sad' button click
        sadBtn.setOnAction(event -> {
            // Close the pop-up
            Stage stage = (Stage) sadBtn.getScene().getWindow();
            stage.close();
            // Display a motivational quote
            Platform.runLater(() -> {
                try {
                    App.openPopUp("motivational-quotes");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        });
    }
}
