package lk.ijse.semisterfinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddSupplier.fxml"))));
        stage.centerOnScreen();
        stage.setTitle("DashBoard");

        stage.show();
    }
}
