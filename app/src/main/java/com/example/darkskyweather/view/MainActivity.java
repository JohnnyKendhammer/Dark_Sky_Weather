package com.example.darkskyweather.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.darkskyweather.R;
import com.example.darkskyweather.model.Currently;
import com.example.darkskyweather.model.Daily;
import com.example.darkskyweather.model.DataItem;
import com.example.darkskyweather.viewmodel.SearchLocationViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
