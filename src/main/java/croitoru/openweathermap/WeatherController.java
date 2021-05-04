package croitoru.openweathermap;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherController {
    @FXML
    public Button submit;
    @FXML
    TextField locationTF;
    @FXML
    ChoiceBox<String> unitChoice;
    @FXML
    TextField descriptionTF;
    @FXML
    Label day1, day2, day3, day4, day5;
    @FXML
    ImageView todayImage;
    @FXML
    ImageView image1, image2, image3, image4, image5;
    @FXML
    Label Descrip1, Descrip2, Descrip3, Descrip4, Descrip5;

    OpenWeatherMapService service;

    public void initialize() {
        String[] unitChoices = {"Celsius", "Fahrenheit"};
        unitChoice.setItems(FXCollections.observableArrayList(unitChoices));
        OpenWeatherMapServiceFactory factory = new OpenWeatherMapServiceFactory();
        OpenWeatherMapService service = factory.newInstance();
    }

    public void onSubmit(ActionEvent actionEvent) {
        String location = locationTF.getText();
        unitChoice.getItems().setAll(FXCollections.observableArrayList("Celsius", "Fahrenheit"));
        int unitIndex = unitChoice.getSelectionModel().getSelectedIndex();
        String unit;
        if (unitIndex == 0) {
            unit = "metric";
        } else {
            unit = "imperial";
        }
        Disposable disposable = service.getWeatherForecast(location, unit)
                // request the data in the background
                .subscribeOn(Schedulers.io())
                // work with the data in the foreground
                .observeOn(Schedulers.trampoline())
                // work with the feed whenever it gets downloaded
                .subscribe(this::onOpenWeatherMapFeed, this::onError);
    }

    public void onOpenWeatherMapFeed(OpenWeatherMapForecast forecast) {
        //current weather
        OpenWeatherMapFeed feed = new OpenWeatherMapFeed();
        descriptionTF.setText(String.valueOf(feed.main.temp));
        ImageView currImage = new ImageView(forecast.list.get(0).weather.get(0).getIconUrl());
        todayImage.setImage(currImage.getImage());
        //next 5 days
        Descrip1.setText(String.valueOf(forecast.getForcastFor(1).main.temp));
        Descrip2.setText(String.valueOf(forecast.getForcastFor(2).main.temp));
        Descrip3.setText(String.valueOf(forecast.getForcastFor(3).main.temp));
        Descrip4.setText(String.valueOf(forecast.getForcastFor(4).main.temp));
        Descrip5.setText(String.valueOf(forecast.getForcastFor(5).main.temp));
    }

    private void onError(Throwable throwable) {

        // this is not the correct way to handle errors
        System.out.println("error occurred");
    }
}