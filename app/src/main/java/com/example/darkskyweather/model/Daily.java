package com.example.darkskyweather.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Daily{

	@SerializedName("summary")
	private String summary;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("icon")
	private String icon;

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
			"Daily{" + 
			"summary = '" + summary + '\'' + 
			",data = '" + data + '\'' + 
			",icon = '" + icon + '\'' + 
			"}";
		}
}