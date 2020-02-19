package com.example.darkskyweather.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.darkskyweather.model.Currently;
import com.example.darkskyweather.model.Daily;
import com.example.darkskyweather.model.Hourly;
import com.example.darkskyweather.model.Minutely;
import com.example.darkskyweather.model.WeatherResponse;
import com.example.darkskyweather.repository.Repository;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchLocationViewModel extends AndroidViewModel {
    private static final String TAG = "SearchLocationViewModel";
    private Repository repo = Repository.getInstance();

    private MutableLiveData<Daily> weatherDailyData = new MutableLiveData<>();
    private MutableLiveData<Hourly> weatherHourlyData = new MutableLiveData<>();
    private MutableLiveData<Currently> weatherCurrentData = new MutableLiveData<>();
    private MutableLiveData<Minutely> weatherMinutelyData = new MutableLiveData<>();
    private MutableLiveData<WeatherResponse> weatherResponseData = new MutableLiveData<>();

    public SearchLocationViewModel(@NonNull Application application) {
        super(application);
    }

    public void getLocationObservable(double latitude, double longitude) {
        repo.getWeatherResponse(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: starts");
                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        Log.d(TAG, "onNext: starts");
                        weatherDailyData.setValue(weatherResponse.getDaily());
                        weatherHourlyData.setValue(weatherResponse.getHourly());
                        weatherCurrentData.setValue(weatherResponse.getCurrently());
                        weatherMinutelyData.setValue(weatherResponse.getMinutely());
                        weatherResponseData.setValue(weatherResponse);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: starts " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: starts");
                    }
                });
    }
    public LiveData<Daily> getDailyWeather() {
        Log.d(TAG, "getDailyWeather(): get Daily");
        return weatherDailyData;
    }

    public LiveData<Hourly> getHourlyWeather() {
        Log.d(TAG, "getHourlyWeather(): get Hourly");
        return weatherHourlyData;
    }

    public LiveData<Currently> getCurrentWeather() {
        Log.d(TAG, "getCurrentWeather(): get Current");
        return weatherCurrentData;
    }

    public LiveData<Minutely> getMinutelyWeather() {
        Log.d(TAG, "getMinutelyWeather(): get Minutely");
        return weatherMinutelyData;
    }

    public LiveData<WeatherResponse> getWeatherResponse() {
        Log.d(TAG, "getMinutelyWeather(): get WeatherResponse");
        return weatherResponseData;
    }

}
