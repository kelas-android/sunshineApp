package net.ruangtedy.java.android.sunshine.model;

import java.util.ArrayList;
import java.util.List;

public class WeatherResult {
	private City city;
	private int cod;
	private double message;
	private int cnt;
	private List<Data> list=new ArrayList<Data>();
	

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public double getMessage() {
		return message;
	}

	public void setMessage(double message) {
		this.message = message;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	

	public List<Data> getList() {
		return list;
	}

	public void setList(List<Data> list) {
		this.list = list;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
}
