package croitoru.openweathermap;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {
    @GET("data/2.5/weather?appid=c710bf77c761ce8cc9543f95911549c0&units=imperial")
    Single<OpenWeatherMapFeed> getCurrentWeather(
            @Query("q") String location,
            @Query("units") String units
    );

    @GET("/data/2.5/forecast?appid=c710bf77c761ce8cc9543f95911549c0")
    Single<OpenWeatherMapForecast> getWeatherForecast(
            @Query("q") String location,
            @Query("units") String units
    );
}