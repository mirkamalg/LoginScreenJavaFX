import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton signInButton;

    @FXML
    private TextFlow textFlow;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private MaterialDesignIconView topIcon;

    DataHandler dataHandler;


    public void signInAction(ActionEvent actionEvent) throws InterruptedException {

        if (!emailTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            Validator validator = new Validator();
            if (validator.isValidEmail(emailTextField.getText())) {
                if (DataHandler.getAccountMap().containsKey(emailTextField.getText())) {
                    if (DataHandler.toSHA512(passwordField.getText(), "$4LT").equals(DataHandler.getAccountMap().get(emailTextField.getText()).getPassword())) {
                        System.out.println("SUCCESS");  //  WHAT TO DO AFTER LOGIN IS SUCCESSFUL
                    }
                } else {
                    emailTextField.setText("No such account exists!");
                    emailTextField.setStyle("-fx-text-inner-color: red; -jfx-label-float: true; -jfx-focus-color:  red; -fx-prompt-text-fill: b2b2b2");

                    emailTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                        emailTextField.setStyle("-fx-text-inner-color: b2b2b2; -jfx-label-float: true; -jfx-focus-color:  #639a67; -fx-prompt-text-fill: b2b2b2");
                    });
                }
            } else {
                emailTextField.setText("Incorrect email format!");
                emailTextField.setStyle("-fx-text-inner-color: red; -jfx-label-float: true; -jfx-focus-color:  red; -fx-prompt-text-fill: b2b2b2");

                emailTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                    emailTextField.setStyle("-fx-text-inner-color: b2b2b2; -jfx-label-float: true; -jfx-focus-color:  #639a67; -fx-prompt-text-fill: b2b2b2");
                });
            }
        }
//        FadeTransition ft = new FadeTransition(Duration.millis(1000), pane);  // Fade out animation
//        ft.setFromValue(1.0);
//        ft.setToValue(0.0);
//        ft.play();
    }

    public void signUpAction(ActionEvent actionEvent) throws IOException {
        SignUpController.initializeSignUpScreen();  //  Adding account to the map
    }
}
