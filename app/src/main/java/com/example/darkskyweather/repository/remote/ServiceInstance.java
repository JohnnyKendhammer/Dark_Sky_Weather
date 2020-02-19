package com.example.darkskyweather.repository.remote;

import com.example.darkskyweather.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceInstance {

    private ServiceInstance() {
    }

    private static class RetrofitInstanceHolder {
        private static final Retrofit INSTANCE = new Retrofit.Builder()
                .baseUrl(Constants.DAKE_SKY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static WeatherService getWeatherService() {
        return RetrofitInstanceHolder.INSTANCE.create(WeatherService.class);
    }
}
