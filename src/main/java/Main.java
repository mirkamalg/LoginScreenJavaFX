import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        primaryStage.setTitle("Welcome");

        Scene mainScene = new Scene(root, 560, 325);
        mainStage.setScene(mainScene);

        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
