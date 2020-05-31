import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField passwordField1;

    @FXML
    private JFXButton signUpButton;

    static Stage signUpStage;

    static void initializeSignUpScreen() throws IOException {
        signUpStage = new Stage();

        signUpStage.initModality(Modality.APPLICATION_MODAL);
        signUpStage.setResizable(false);
        signUpStage.setTitle("Sign up");

        Parent signUpWindow = FXMLLoader.load(SignUpController.class.getResource("SignUpScreen.fxml"));

        Scene signUpScene = new Scene(signUpWindow, 600, 370);

        signUpStage.setScene(signUpScene);
        signUpStage.showAndWait();
    }

    @FXML
    void signUpAction(ActionEvent event) {
        Validator validator = new Validator();

        if (!emailTextField.getText().isEmpty() && !passwordField.getText().isEmpty() && !passwordField1.getText().isEmpty()) {
            if (passwordField.getText().equals(passwordField1.getText())) {
                if (validator.isValidEmail(emailTextField.getText()) && validator.isValidPassword(passwordField.getText())) {

                    boolean answer =  DataHandler.addAccount(emailTextField.getText(), passwordField.getText());
                    if (answer) {
                        signUpStage.close();
                    }else {
                        emailTextField.setText("This email is already in use!");
                    }
                } else {
                    if (!validator.isValidEmail(emailTextField.getText())) {
                        emailTextField.setStyle("-fx-text-inner-color: red; -jfx-label-float: true; -jfx-focus-color:  red; -fx-prompt-text-fill: b2b2b2");

                        emailTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                            emailTextField.setStyle("-fx-text-inner-color: b2b2b2; -jfx-label-float: true; -jfx-focus-color:  #639a67; -fx-prompt-text-fill: b2b2b2");
                        });
                    }

                    if (!validator.isValidPassword(passwordField.getText())) {
                        passwordField.setStyle("-fx-text-inner-color: red; -jfx-label-float: true; -jfx-focus-color:  red; -fx-prompt-text-fill: b2b2b2");

                        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
                            passwordField.setStyle("-fx-text-inner-color: b2b2b2; -jfx-label-float: true; -jfx-focus-color:  #639a67; -fx-prompt-text-fill: b2b2b2");
                        });
                        passwordField1.clear();
                    }
                }
            }else {
                passwordField1.setStyle("-fx-text-inner-color: red; -jfx-label-float: true; -jfx-focus-color:  red; -fx-prompt-text-fill: b2b2b2");

                passwordField1.textProperty().addListener((observable, oldValue, newValue) -> {
                    passwordField1.setStyle("-fx-text-inner-color: b2b2b2; -jfx-label-float: true; -jfx-focus-color:  #639a67; -fx-prompt-text-fill: b2b2b2");
                });
            }
        }
    }

}
