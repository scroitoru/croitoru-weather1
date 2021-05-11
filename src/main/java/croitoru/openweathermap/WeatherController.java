package croitoru.openweathermap;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherController {
    @FXML
    TextField locationTF;
    @FXML
    ChoiceBox<String> unitChoice;
    @FXML
    Label currDescription;
    @FXML
    ImageView todayImage;
    @FXML
    ImageView image1, image2, image3, image4, image5;
    @FXML
    Label descrip1, descrip2, descrip3, descrip4, descrip5;

    OpenWeatherMapService service;

    public WeatherController(OpenWeatherMapService service){
        this.service = service;
    }

    public void initialize() {
        String[] unitChoices = {"Fahrenheit", "Celsius"};
        unitChoice.setItems(FXCollections.observableArrayList(unitChoices));
    }

    public void onSubmit(ActionEvent actionEvent) {
        String location = locationTF.getText();
        String unit = String.valueOf(unitChoice.getValue()).equals("Fahrenheit")? "imperial" : "metric";
        Disposable disposable = service.getWeatherForecast(location, unit)
                // request the data in the background
                .subscribeOn(Schedulers.io())
                // work with the data in the foreground
                .observeOn(Schedulers.trampoline())
                // work with the feed whenever it gets downloaded
                .subscribe(this::onOpenWeatherMapFeed, this::onError);
    }

    public void onOpenWeatherMapFeed(OpenWeatherMapForecast forecast) {
        //current weather icon
        ImageView currImage = new ImageView(forecast.list.get(0).weather.get(0).getIconUrl());
        todayImage.setImage(currImage.getImage());
//        //curr weather & next 5 days temp
        Platform.runLater(() -> {
            currDescription.setText(String.valueOf(forecast.getForecastFor(0).main.temp));
            descrip1.setText(String.valueOf(forecast.getForecastFor(1).main.temp));
            descrip2.setText(String.valueOf(forecast.getForecastFor(2).main.temp));
            descrip3.setText(String.valueOf(forecast.getForecastFor(3).main.temp));
            descrip4.setText(String.valueOf(forecast.getForecastFor(4).main.temp));
            descrip5.setText(String.valueOf(forecast.getForecastFor(5).main.temp));
        });
        //next 5 days icons
        image1.setImage(new Image(forecast.getForecastFor(1).weather.get(0).getIconUrl()));
        image2.setImage(new Image(forecast.getForecastFor(2).weather.get(0).getIconUrl()));
        image3.setImage(new Image(forecast.getForecastFor(3).weather.get(0).getIconUrl()));
        image4.setImage(new Image(forecast.getForecastFor(4).weather.get(0).getIconUrl()));
        image5.setImage(new Image(forecast.getForecastFor(5).weather.get(0).getIconUrl()));
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}