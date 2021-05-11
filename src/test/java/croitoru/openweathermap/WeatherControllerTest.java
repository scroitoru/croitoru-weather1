package croitoru.openweathermap;

import io.reactivex.rxjava3.core.Single;
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
public class WeatherControllerTest {

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }

    //working test
    @Test
    public void initialize() {
        //given
       OpenWeatherMapService service = mock(OpenWeatherMapService.class);
       WeatherController controller = new WeatherController(service);
       controller.unitChoice = mock(ChoiceBox.class);
        //when
        controller.initialize();

        //then
        verify(controller.unitChoice).setItems(any());

    }

    //working test
    @Test
    public void onSubmit(){
        //given
        OpenWeatherMapService service = mock(OpenWeatherMapService.class);
        WeatherController controller = new WeatherController(service);
        controller.locationTF = mock(TextField.class);
        controller.unitChoice = mock(ChoiceBox.class);
        doReturn("New York").when(controller.locationTF).getText();
        doReturn("Celsius").when(controller.unitChoice).getValue();
        doReturn(Single.never()).when(service).getWeatherForecast("New York", "metric");

        //when
        controller.onSubmit(mock(ActionEvent.class));

        //then
        verify(controller.locationTF).getText();
        verify(controller.unitChoice).getValue();
        verify(service).getWeatherForecast("New York", "metric");
    }

    //DIDN"T DO THIS TEST YET
    @Test
    public void onOpenWeatherMapFeed(){
        //given
        OpenWeatherMapService service = mock(OpenWeatherMapService.class);
        WeatherController controller = new WeatherController(service);
        OpenWeatherMapForecast forecast = mock(OpenWeatherMapForecast.class);
        OpenWeatherMapFeed feed = mock(OpenWeatherMapFeed.class);

        //when
        controller.onOpenWeatherMapFeed(forecast);

//        //then
//        verify(controller.)
        //verify(controller.descriptionTF.setText(String.valueOf(feed.main.temp)));
    }

}