package lk.ijse.gdse.ormcourseworkproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1120, 721);
        stage.setTitle("Hello!");

        Image image = new Image(getClass().getResourceAsStream("/images/logodesign.png"));
        stage.getIcons().add(image);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}