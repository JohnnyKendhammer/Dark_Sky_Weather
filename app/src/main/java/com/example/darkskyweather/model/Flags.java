package com.example.darkskyweather.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Flags{

	@SerializedName("nearest-station")
	private double nearestStation;

	@SerializedName("sources")
	private List<String> sources;

	@SerializedName("units")
	private String units;

	public void setNearestStation(double nearestStation){
		this.nearestStation = nearestStation;
	}

	public double getNearestStation(){
		return nearestStation;
	}

	public void setSources(List<String> sources){
		this.sources = sources;
	}

	public List<String> getSources(){
		return sources;
	}

	public void setUnits(String units){
		this.units = units;
	}

	public String getUnits(){
		return units;
	}

	@Override
 	public String toString(){
		return 
			"Flags{" + 
			"nearest-station = '" + nearestStation + '\'' + 
			",sources = '" + sources + '\'' + 
			",units = '" + units + '\'' + 
			"}";
		}
}