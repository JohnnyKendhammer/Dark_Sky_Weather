package com.example.darkskyweather.view;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.darkskyweather.R;
import com.example.darkskyweather.model.Currently;
import com.example.darkskyweather.model.Daily;
import com.example.darkskyweather.model.DataItem;
import com.example.darkskyweather.model.Hourly;
import com.example.darkskyweather.model.Minutely;
import com.example.darkskyweather.model.WeatherResponse;
import com.example.darkskyweather.viewmodel.SearchLocationViewModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {
    //Sets global variables for LiveData
    private SearchLocationViewModel mSearchLocationViewModel;
    private LiveData<Daily> dailyLiveData;
    private LiveData<Currently> currentLiveData;
    private LiveData<Hourly> hourlyLiveData;
    private LiveData<Minutely> minutelyLiveData;
    private LiveData<WeatherResponse> weatherResponseLiveData;

    //Sets global variable for TextViews, Buttons, Etc.
    private TextView tvLocation;
    private TextView tvStatus;
    private TextView tvDate;
    private TextView tvBigTemp;
    private TextView tvHighTemp;
    private TextView tvLowTemp;

    private ImageView ivNewLocation;
    private Toolbar toolbar;
    private Context mContext;


    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        mContext = view.getContext();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchLocationViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
                .create(SearchLocationViewModel.class);
        mSearchLocationViewModel.getLocationObservable(42.3601, -71.0589);

        setupObserver();


        tvLocation = view.findViewById(R.id.tvLocation);
        tvBigTemp = view.findViewById(R.id.tvBigTemp);
        tvDate = view.findViewById(R.id.tvDate);
        tvStatus = view.findViewById(R.id.tvStatus);
        tvLowTemp = view.findViewById(R.id.tvLowTemp);
        tvHighTemp = view.findViewById(R.id.tvHighTemp);
        ivNewLocation = view.findViewById(R.id.ivAdd);
        toolbar = view.findViewById(R.id.toolbar);

        setLocation(42.3601, -71.0589);

        ivNewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog fbDialogue = new Dialog(v.getContext(), android.R.style.Theme_Black_NoTitleBar);
                fbDialogue.getWindow()
                        .setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                fbDialogue.setContentView(R.layout.layout_change_location);
                fbDialogue.setCancelable(true);
                fbDialogue.show();

                Button btnGo = fbDialogue.findViewById(R.id.btnGo);
                btnGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText etLocation = fbDialogue.findViewById(R.id.etNewLocation);
                        setNewLocation(mContext, etLocation.getText().toString());
                        fbDialogue.cancel();
                    }
                });
            }
        });

    }

    private void setupObserver() {
        mSearchLocationViewModel.getDailyWeather().observe(getViewLifecycleOwner(), new Observer<Daily>() {
            @Override
            public void onChanged(Daily daily) {
                dailyLiveData = mSearchLocationViewModel.getDailyWeather();
                DataItem di = daily.getData().get(0);
                int highTemp = (int) Math.round(Double.parseDouble(di.getTemperatureHigh()));
                int lowTemp = (int) Math.round(Double.parseDouble(di.getTemperatureLow()));
                tvHighTemp.setText(String.valueOf(highTemp) + " °F");
                tvLowTemp.setText(String.valueOf(lowTemp) + " °F");

            }
        });

        mSearchLocationViewModel.getCurrentWeather().observe(getViewLifecycleOwner(), new Observer<Currently>() {
            @Override
            public void onChanged(Currently currently) {
                currentLiveData = mSearchLocationViewModel.getCurrentWeather();

                //Set the TextViews from the Currently Class
                tvBigTemp.setText(Math.round(currently.getTemperature()) + "°");
                setDate(currently.getTime());
                tvStatus.setText(currently.getSummary());

            }
        });

        mSearchLocationViewModel.getHourlyWeather().observe(getViewLifecycleOwner(), new Observer<Hourly>() {
            @Override
            public void onChanged(Hourly hourly) {
                hourlyLiveData = mSearchLocationViewModel.getHourlyWeather();

            }
        });

        mSearchLocationViewModel.getMinutelyWeather().observe(getViewLifecycleOwner(), new Observer<Minutely>() {
            @Override
            public void onChanged(Minutely minutely) {
                minutelyLiveData = mSearchLocationViewModel.getMinutelyWeather();
            }
        });

        mSearchLocationViewModel.getWeatherResponse().observe(getViewLifecycleOwner(), new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {


            }
        });
    }


    private void setDate(int time) {
        String dateAsText = new SimpleDateFormat("EEE, h:mm aa")
                .format(new Date(time * 1000L));
        tvDate.setText(dateAsText);
    }

    private void setNewLocation(Context context, String location) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocationName(location, 5);
            double lat = addresses.get(0).getLatitude();
            double lng = addresses.get(0).getLongitude();
            mSearchLocationViewModel.getLocationObservable(lat, lng);
            setupObserver();
            tvLocation.setText(location);
            getActivity().setTitle("Current conditions in " + location);

        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void setLocation(double latitude, double longitude){

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(mContext, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();

            tvLocation.setText(city);
            getActivity().setTitle("Current conditions in " + city);
        }catch (IOException e){
            e.getMessage();
        }
    }

}
