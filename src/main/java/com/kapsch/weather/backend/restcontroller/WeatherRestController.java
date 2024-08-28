package com.kapsch.weather.backend.restcontroller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kapsch.weather.backend.model.Request;
import com.kapsch.weather.backend.service.IMasterDetailsService;
import com.kapsch.weather.backend.service.IWeatherService;
import com.kapsch.weather.backend.util.Util;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class WeatherRestController {
	
private static final Logger logger = LoggerFactory.getLogger(WeatherRestController.class);
	
	@Autowired
	private IWeatherService weatherService;
	
	@Autowired
	private IMasterDetailsService masterDetailService;
	
	
	/**
	 * search the weather to API and then 
	 * save the data into database.
	 * Finally export data to CVS file
	 * @param request
	 * @return
	 */
	@PostMapping("/search")
	public void search(@RequestBody Request request, HttpServletResponse httpResponse) {
	    logger.info(String.valueOf(request.getLatitude()));
	    logger.info(String.valueOf(request.getLongitude()));

	    Optional.ofNullable(weatherService.getCurrentWeather(request.getLatitude(), request.getLongitude()))
	            .map(Util::processMasterDetail)
	            .map(masterDetailService::saveMasterDetail)
	            .ifPresent(
	                master -> {
	                    logger.info(master.toString());
	                    try {
	                        masterDetailService.exportDetailsToCsv(httpResponse);
	                    } catch (IOException e) {
	                        logger.error(e.getMessage());
	                    }
	                }
	               
	            );
	 
	}

}
