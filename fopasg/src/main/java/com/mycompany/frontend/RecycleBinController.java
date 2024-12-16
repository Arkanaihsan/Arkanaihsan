package com.mycompany.frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;

public class RecycleBinController {
    @FXML
    private FlowPane diaryItemsFlowPane;; // Reference to the FlowPane in diary-recycle-bin.fxml

    // A class representing a diary item (Can Change Later)
    static class DiaryItem {
        String title;
        String date;
        String daysLeft;

        DiaryItem(String title, String date, String daysLeft) {
            this.title = title;
            this.date = date;
            this.daysLeft = daysLeft;
        }
    }

    @FXML
    public void initialize() {
        // Sample data for diary items
        List<DiaryItem> diaryItems = new ArrayList<>();
        diaryItems.add(new DiaryItem("Diary 1", "1 December 2024", "29 days left"));
        diaryItems.add(new DiaryItem("Diary 2", "5 December 2024", "15 days left"));
        diaryItems.add(new DiaryItem("Diary 3", "10 December 2024", "5 days left"));
        diaryItems.add(new DiaryItem("Diary 4", "10 December 2024", "5 days left"));
        diaryItems.add(new DiaryItem("Diary 1", "1 December 2024", "29 days left"));
        diaryItems.add(new DiaryItem("Diary 2", "5 December 2024", "15 days left"));
        diaryItems.add(new DiaryItem("Diary 3", "10 December 2024", "5 days left"));
        diaryItems.add(new DiaryItem("Diary 4", "10 December 2024", "5 days left"));
        diaryItems.add(new DiaryItem("Diary 1", "1 December 2024", "29 days left"));
        diaryItems.add(new DiaryItem("Diary 2", "5 December 2024", "15 days left"));
        diaryItems.add(new DiaryItem("Diary 3", "10 December 2024", "5 days left"));
        diaryItems.add(new DiaryItem("Diary 4", "10 December 2024", "5 days left"));
        diaryItems.add(new DiaryItem("Diary 1", "1 December 2024", "29 days left"));
        diaryItems.add(new DiaryItem("Diary 2", "5 December 2024", "15 days left"));
        diaryItems.add(new DiaryItem("Diary 3", "10 December 2024", "5 days left"));
        diaryItems.add(new DiaryItem("Diary 4", "10 December 2024", "5 days left"));

        // Loop through the list and create a Pane for each diary item
        for (DiaryItem item : diaryItems) {
            Pane diaryItemPane = createDiaryItemPane(item);
            diaryItemsFlowPane.getChildren().add(diaryItemPane); // Add the pane to the FlowPane
        }
    }

    private Pane createDiaryItemPane(DiaryItem item) {
        Pane pane = new Pane();
        pane.setPrefSize(195.0, 65.0);
        pane.setStyle("-fx-background-color: #F1F1F1;");

        // Image
        ImageView imageView = new ImageView(
                new Image(getClass().getResourceAsStream("/com/mycompany/frontend/images/diary-icon.png")));
        imageView.setFitHeight(35.0);
        imageView.setFitWidth(42.0);
        imageView.setLayoutX(14.0);
        imageView.setLayoutY(15.0);

        // Title Label
        Label titleLabel = new Label(item.title);
        titleLabel.setLayoutX(74.0);
        titleLabel.setLayoutY(23.0);
        titleLabel.setStyle("-fx-background-color: #F1F1F1;");
        titleLabel.setTextFill(javafx.scene.paint.Color.web("#9abf80"));
        titleLabel.setFont(javafx.scene.text.Font.font("Roboto Bold", 15));

        // Days Left Label
        Label daysLeftLabel = new Label(item.daysLeft);
        daysLeftLabel.setLayoutX(140.0);
        daysLeftLabel.setLayoutY(45.0);
        daysLeftLabel.setStyle("-fx-background-color: #F1F1F1;");
        daysLeftLabel.setTextFill(javafx.scene.paint.Color.web("#1c325b"));
        daysLeftLabel.setFont(javafx.scene.text.Font.font("Roboto Bold", 8));

        // Date Label
        Label dateLabel = new Label(item.date);
        dateLabel.setLayoutX(135.0);
        dateLabel.setLayoutY(10.0);
        dateLabel.setStyle("-fx-background-color: #F1F1F1;");
        dateLabel.setTextFill(javafx.scene.paint.Color.web("#8f8f8f"));
        dateLabel.setFont(javafx.scene.text.Font.font("Roboto Bold", 6));

        // Hover Pane (Initially Hidden)
        Pane hoverPane = new Pane();
        hoverPane.setPrefSize(195.0, 65.0);
        hoverPane.setStyle("-fx-background-color: rgba(30,30,30,0.7); visibility: hidden;");

        // Hover Icons
        ImageView viewIcon = new ImageView(
                new Image(getClass().getResource("/com/mycompany/frontend/images/view-icon.png").toString()));
        viewIcon.setLayoutX(173.0);
        viewIcon.setLayoutY(7.0);
        viewIcon.setFitHeight(14.0);
        viewIcon.setFitWidth(14.0);
        hoverPane.getChildren().add(viewIcon);

        ImageView restoreIcon = new ImageView(
                new Image(getClass().getResource("/com/mycompany/frontend/images/restore-icon.png").toString()));
        restoreIcon.setLayoutX(173.0);
        restoreIcon.setLayoutY(25.0);
        restoreIcon.setFitHeight(14.0);
        restoreIcon.setFitWidth(14.0);
        hoverPane.getChildren().add(restoreIcon);

        ImageView deleteIcon = new ImageView(
                new Image(getClass().getResource("/com/mycompany/frontend/images/delete-icon.png").toString()));
        deleteIcon.setLayoutX(173.0);
        deleteIcon.setLayoutY(43.0);
        deleteIcon.setFitHeight(14.0);
        deleteIcon.setFitWidth(14.0);
        hoverPane.getChildren().add(deleteIcon);

        // Add components to the pane
        pane.getChildren().addAll(imageView, titleLabel, daysLeftLabel, dateLabel, hoverPane);

        // Hover effect for dynamically created pane
        pane.setOnMouseEntered(
                e -> hoverPane.setStyle("-fx-background-color: rgba(30,30,30,0.7); visibility: visible;"));
        pane.setOnMouseExited(e -> hoverPane.setStyle("-fx-background-color: rgba(30,30,30,0.7); visibility: hidden;"));

        // Event handler for restore icon
        restoreIcon.setOnMouseClicked(e -> {
            // Perform the restore action here (e.g., navigate to restore page or restore
            // item)
            handleRestore(item, pane); // Custom method to handle restoration
        });

        // Event handler for delete icon
        deleteIcon.setOnMouseClicked(e -> {
            // Perform the delete action here (e.g., navigate to delete page or delete item)
            handleDelete(item, pane); // Custom method to handle deletion
        });

        return pane;
    }

    // Method to handle the delete action
    private void handleDelete(DiaryItem item, Pane pane) {

        // Show a delete confirmation page
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("pop-up-box.fxml"));
            Parent root = loader.load();

            PopUpBoxController controller = loader.getController();
            controller.setConfirmationText("Are you sure you want to permanently delete this entry?");
            // Set the pop-up as a modal window so it blocks interaction with the main
            // window
            Stage popupStage = new Stage();
            popupStage.initStyle(StageStyle.UNDECORATED); // Removes the title bar and close button
            // Makes the window modal (blocks interaction with the main window)
            popupStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            popupStage.setScene(scene); // Change the scene of the stage
            popupStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Method to handle the restore action
    private void handleRestore(DiaryItem item, Pane pane) {

        // Show a restore confirmation page
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("pop-up-box.fxml"));
            Parent root = loader.load();

            PopUpBoxController controller = loader.getController();
            controller.setConfirmationText("Do you confirm to restore this entry?");
            // Set the pop-up as a modal window so it blocks interaction with the main
            // window
            Stage popupStage = new Stage();
            popupStage.initStyle(StageStyle.UNDECORATED); // Removes the title bar and close button
            // Makes the window modal (blocks interaction with the main window)
            popupStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            popupStage.setScene(scene); // Change the scene of the stage
            popupStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}