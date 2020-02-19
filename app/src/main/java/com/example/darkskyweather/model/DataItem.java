package com.example.darkskyweather.model;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("precipProbability")
	private double precipProbability;

	@SerializedName("precipIntensity")
	private double precipIntensity;

	@SerializedName("time")
	private int time;

	@SerializedName("temperatureHigh")
	private String temperatureHigh;

	@SerializedName("temperatureLow")
	private String temperatureLow;

	public String getTemperatureHigh() {
		return temperatureHigh;
	}

	public void setTemperatureHigh(String temperatureHigh) {
		this.temperatureHigh = temperatureHigh;
	}

	public String getTemperatureLow() {
		return temperatureLow;
	}

	public void setTemperatureLow(String temperatureLow) {
		this.temperatureLow = temperatureLow;
	}

	public void setPrecipProbability(int precipProbability){
		this.precipProbability = precipProbability;
	}

	public double getPrecipProbability(){
		return precipProbability;
	}

	public void setPrecipIntensity(int precipIntensity){
		this.precipIntensity = precipIntensity;
	}

	public double getPrecipIntensity(){
		return precipIntensity;
	}

	public void setTime(int time){
		this.time = time;
	}

	public int getTime(){
		return time;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"precipProbability = '" + precipProbability + '\'' + 
			",precipIntensity = '" + precipIntensity + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}