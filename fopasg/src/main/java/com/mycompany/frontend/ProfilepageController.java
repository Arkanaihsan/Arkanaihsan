/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.frontend;

import java.net.URL;
import javafx.fxml.Initializable;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ProfilepageController extends Initializable {
      @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private ImageView usernameEdit;

    @FXML
    private ImageView emailEdit;

    @FXML
    private ImageView passwordEdit;

    @FXML
    private Text passwordMsg;

    @FXML
    private Button submitBtn;


    /*** INITILIZATION OF THE CONTROLLER
     * 
     * ***/
    @FXML
    public void initialize() {
        // Inherit Super Class's initialize()
        super.initialize(); 

        // Set the data
        username.setText("test123");
        email.setText("test@gmail.com");
        password.setText("12345678");

        // Default cannot edit
        username.setEditable(false);
        email.setEditable(false);
        password.setEditable(false);

        // Unless user click on edit icon
        usernameEdit.setOnMouseClicked(e->{
            username.setEditable(true);
            username.setStyle("-fx-background-color: #F1F1F1;");
            username.requestFocus();
        });

        emailEdit.setOnMouseClicked(e->{
            email.setEditable(true);
            email.setStyle("-fx-background-color: #F1F1F1;");
            email.requestFocus();
        });

        passwordEdit.setOnMouseClicked(e->{
            password.setEditable(true);
            password.setStyle("-fx-background-color: #F1F1F1;");
            password.requestFocus();
        });

        // Check for password strength
        password.setOnKeyTyped(e->{
            // Weak password
            if (password.getText().length() < 6){ // NEED TO CHANGE THE FORMAT COMPARE
                password.setStyle("-fx-background-color: #FF9696;");
                passwordMsg.setText("Password strength weak.");
                passwordMsg.setStyle("-fx-fill: #FF9696;");
            }
            // Moderate password 
            else if (password.getText().equals("123456")){ // NEED TO CHANGE THE FORMAT COMPARE
                password.setStyle("-fx-background-color: #669A9D;");
                passwordMsg.setText("Password strength moderate.");
                passwordMsg.setStyle("-fx-fill: #669A9D;");
            }
            // Strong password 
            else if (password.getText().equals("abc123")){ // NEED TO CHANGE THE FORMAT COMPARE
                password.setStyle("-fx-background-color: #9ABF80;");
                passwordMsg.setText("Password strength strong.");
                passwordMsg.setStyle("-fx-fill: #9ABF80;");
            }
        });

    /**
     * Initializes the controller class.
     */
    
        // TODO
    }    
    
}
