package com.kapsch.weather.backend.service;

import com.kapsch.weather.backend.model.WeatherResponse;

public interface IWeatherService {
	
	public WeatherResponse getCurrentWeather(double latitude, double d);

}
