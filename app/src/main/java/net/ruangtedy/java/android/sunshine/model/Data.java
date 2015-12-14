package net.ruangtedy.java.android.sunshine.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties("datestr")
public class Data {
	private int dt;
	private Temperature temp;
	private Main main;
	private double pressure;
	private int humidity;
	private double speed;
	private double deg;
	@JsonProperty("clouds")
	private double clouds;
	private double rain;

	private List<Weather> weather;
	private Wind wind;
	private String datestr;

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

	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}


	public Temperature getTemp() {
		return temp;
	}
	public void setTemp(Temperature temp) {
		this.temp = temp;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDeg() {
		return deg;
	}
	public void setDeg(double deg) {
		this.deg = deg;
	}
	public double getClouds() {
		return clouds;
	}
	public void setClouds(double clouds) {
		this.clouds = clouds;
	}
	public double getRain() {
		return rain;
	}
	public void setRain(double rain) {
		this.rain = rain;
	}
	public String getDatestr() {
		Date time=new java.util.Date((long)dt*1000);

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm:ss a",Locale.ENGLISH);
		datestr=df.format(time);
		return datestr;
	}
	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}





}
