package croitoru.openweathermap;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;
public class WeatherControllerTest extends TestCase {

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }

    @Test
    public void Initialize() {
        //given
        WeatherController controller = given();
        String[] unitChoices = {"Celsius", "Fahrenheit"};
        controller.unitChoice.setItems(FXCollections.observableArrayList(unitChoices));

        //when
        controller.initialize();

        //then
        verify(!controller.unitChoice.getSelectionModel().isEmpty());
    }

    @Test
    public void onSubmit(){
        //given
        WeatherController controller = given();

        //when
        controller.onSubmit(mock(ActionEvent.class));

        //then
        verify(controller.locationTF.getText());
        verify(String.valueOf(controller.unitChoice.getValue().equals("Fahrenheit")? "imperial" : "metric"));
    }

    @Test
    public void onOpenWeatherMapFeed(){
        //given
        WeatherController controller = given();
        OpenWeatherMapForecast forecast = mock(OpenWeatherMapForecast.class);
        OpenWeatherMapFeed feed = mock(OpenWeatherMapFeed.class);

        //when
        controller.onOpenWeatherMapFeed(forecast);

        //then
        //verify(controller.descriptionTF.setText(String.valueOf(feed.main.temp)));
    }

    public WeatherController given(){
        WeatherController controller = new WeatherController();
        controller.submit = mock(Button.class);
        controller.locationTF= mock(TextField.class);
        controller.unitChoice = mock(ChoiceBox.class);
        controller.descriptionTF = mock(Label.class);
        controller.todayImage = mock(ImageView.class);
        controller.image1 = mock(ImageView.class);
        controller.image2 = mock(ImageView.class);
        controller.image3 = mock(ImageView.class);
        controller.image4 = mock(ImageView.class);
        controller.image5 = mock(ImageView.class);
        controller.descrip1 = mock(Label.class);
        controller.descrip2 = mock(Label.class);
        controller.descrip3 = mock(Label.class);
        controller.descrip4 = mock(Label.class);
        controller.descrip5 = mock(Label.class);
        return controller;
    }
}