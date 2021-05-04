package croitoru.openweathermap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WeatherApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/weather_application.fxml"));
        root.setStyle("-fx-background-color:lightblue;"
                        + "-fx-border-style: solid inside;"
                        + "-fx-border-color: black;"
                        + "-fx-border-width: 5;");

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Weather Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

