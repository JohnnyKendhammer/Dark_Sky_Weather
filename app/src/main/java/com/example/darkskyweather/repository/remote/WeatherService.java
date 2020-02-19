package com.example.darkskyweather.repository.remote;

import com.example.darkskyweather.model.WeatherResponse;
import com.example.darkskyweather.utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {


    @GET("/forecast/{key}/{latitude},{longitude}")
    Observable<WeatherResponse> getWeather(
            @Path("key") String key,
            @Path("latitude") double latitude,
            @Path("longitude") double longitude
    );
}
