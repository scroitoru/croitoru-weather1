package croitoru.openweathermap;

import io.reactivex.rxjava3.core.Single;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
        controller.todayImage = mock(ImageView.class);
        controller.currDescription = mock(Label.class);
        controller.descrip1 = mock(Label.class);
        controller.descrip2 = mock(Label.class);
        controller.descrip3 = mock(Label.class);
        controller.descrip4= mock(Label.class);
        controller.descrip5 = mock(Label.class);
        controller.image1 = mock(ImageView.class);
        controller.image2 = mock(ImageView.class);
        controller.image3 = mock(ImageView.class);
        controller.image4 = mock(ImageView.class);
        controller.image5 = mock(ImageView.class);
        doReturn(any()).when(controller.currDescription).setText(anyString());
//        doReturn(anyInt()).when(controller.currDescription).setText(String.valueOf(forecast.getForecastFor(0).main.temp));
//        doReturn(anyInt()).when(controller.descrip1).setText(String.valueOf(forecast.getForecastFor(1).main.temp));
//        doReturn(anyInt()).when(controller.descrip2).setText(String.valueOf(forecast.getForecastFor(2).main.temp));
//        doReturn(anyInt()).when(controller.descrip3).setText(String.valueOf(forecast.getForecastFor(3).main.temp));
//        doReturn(anyInt()).when(controller.descrip4).setText(String.valueOf(forecast.getForecastFor(4).main.temp));
//        doReturn(anyInt()).when(controller.descrip5).setText(String.valueOf(forecast.getForecastFor(5).main.temp));

        //when
        controller.onOpenWeatherMapFeed(forecast);

       //then
        verify(controller.currDescription).setText(anyString());
//        verify(controller.currDescription).setText(String.valueOf(forecast.getForecastFor(0).main.temp));
//        verify(controller.descrip1).setText(String.valueOf(forecast.getForecastFor(1).main.temp));
//        verify(controller.descrip2).setText(String.valueOf(forecast.getForecastFor(2).main.temp));
//        verify(controller.descrip3).setText(String.valueOf(forecast.getForecastFor(3).main.temp));
//        verify(controller.descrip4).setText(String.valueOf(forecast.getForecastFor(4).main.temp));
//        verify(controller.descrip5).setText(String.valueOf(forecast.getForecastFor(5).main.temp));


    }

}