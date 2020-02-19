package com.example.darkskyweather.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Hourly{

	@SerializedName("summary")
	private String summary;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("icon")
	private String icon;

	@SerializedName("temperature")
	private String temperature;

	@SerializedName("apparentTemperature")
	private String apparentTemperature;

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getApparentTemperature() {
		return apparentTemperature;
	}

	public void setApparentTemperature(String apparentTemperature) {
		this.apparentTemperature = apparentTemperature;
	}

	public void setSummary(String summary){
		this.summary = summary;
	}

	public String getSummary(){
		return summary;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	@Override
 	public String toString(){
		return 
			"Hourly{" + 
			"summary = '" + summary + '\'' + 
			",data = '" + data + '\'' + 
			",icon = '" + icon + '\'' + 
			"}";
		}
}