package com.kapsch.weather.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Detail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private double temperature;
	private double windspeed;
	private double winddirection;
	private String timeUTC;
	private String timeLocalTime;
	
	@OneToOne(mappedBy = "detail")
	private Master master;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getWindspeed() {
		return windspeed;
	}

	public void setWindspeed(double windspeed) {
		this.windspeed = windspeed;
	}

	public double getWinddirection() {
		return winddirection;
	}

	public void setWinddirection(double winddirection) {
		this.winddirection = winddirection;
	}

	public String getTimeUTC() {
		return timeUTC;
	}

	public void setTimeUTC(String timeUTC) {
		this.timeUTC = timeUTC;
	}

	public String getTimeLocalTime() {
		return timeLocalTime;
	}

	public void setTimeLocalTime(String timeLocalTime) {
		this.timeLocalTime = timeLocalTime;
	}

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}

}
