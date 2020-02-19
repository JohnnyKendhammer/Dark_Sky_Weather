package com.example.darkskyweather.repository;

import com.example.darkskyweather.model.WeatherResponse;
import com.example.darkskyweather.repository.remote.ServiceInstance;
import com.example.darkskyweather.utils.Constants;

import io.reactivex.Observable;

public class Repository {

    private Repository(){

    }
    private static class RepositoryInstanceHolder {
        private static final Repository INSTANCE = new Repository();
    }

    public static Repository getInstance() {
        return RepositoryInstanceHolder.INSTANCE;
    }

    public Observable<WeatherResponse> getWeatherResponse(double latitude, double longitude) {
        return ServiceInstance.getWeatherService().getWeather(Constants.DARK_SKY_KEY, latitude, longitude);
    }

}
