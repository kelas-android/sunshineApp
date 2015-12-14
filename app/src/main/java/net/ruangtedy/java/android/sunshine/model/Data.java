package net.ruangtedy.java.android.sunshine.model;

import java.util.List;

public class Data {
	private int dt;
	private Main main;
	private List<Weather> weather;
	private Clouds clouds;
	private Wind wind;
	private Rain rain;
	private Snow snow;
	private Sys2 sys;
	private String dt_txt;
	public int getDt() {
		return dt;
	}
	public void setDt(int dt) {
		this.dt = dt;
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public List<Weather> getWeather() {
		return weather;
	}
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}
	public Clouds getClouds() {
		return clouds;
	}
	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public Rain getRain() {
		return rain;
	}
	public void setRain(Rain rain) {
		this.rain = rain;
	}

	public Sys2 getSys() {
		return sys;
	}
	public void setSys(Sys2 sys) {
		this.sys = sys;
	}
	public String getDt_txt() {
		return dt_txt;
	}



	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}

	public Snow getSnow() {
		return snow;
	}

	public void setSnow(Snow snow) {
		this.snow = snow;
	}
	

}
