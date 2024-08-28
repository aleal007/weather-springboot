package com.kapsch.weather.backend.util;

import com.kapsch.weather.backend.dto.MasterDetailDto;
import com.kapsch.weather.backend.entity.Detail;
import com.kapsch.weather.backend.entity.Master;
import com.kapsch.weather.backend.model.WeatherResponse;

public class Util {
	
	public static MasterDetailDto processMasterDetail(WeatherResponse data){
		
		Detail detail = new Detail();
		detail.setTemperature(data.getCurrent_weather().getTemperature());
		detail.setTimeLocalTime(data.getCurrent_weather_units().getTime());
		detail.setTimeUTC(data.getCurrent_weather().getTime());
		detail.setWindspeed(data.getCurrent_weather().getWindspeed());
		detail.setWinddirection(data.getCurrent_weather().getWinddirection());
		
		
		Master master = new Master();
		master.setLatitude(data.getLatitude());
		master.setLongitude(data.getLongitude());
		master.setDetail(detail);
		
		MasterDetailDto dto = new MasterDetailDto();
		dto.setMaster(master);
		dto.setDetail(detail);
		
		return dto;
		
	}

}
