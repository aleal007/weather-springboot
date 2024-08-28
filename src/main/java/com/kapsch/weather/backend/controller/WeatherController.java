package com.kapsch.weather.backend.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kapsch.weather.backend.model.Request;
import com.kapsch.weather.backend.service.IMasterDetailsService;
import com.kapsch.weather.backend.service.IWeatherService;
import com.kapsch.weather.backend.util.Util;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class WeatherController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	private IWeatherService weatherService;
	
	@Autowired
	private IMasterDetailsService masterDetailService;
	
	/**
	 * redirect to index page
	 * @return
	 */
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * page error
	 * @return
	 */
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
	
	/**
	 * search the weather to API and then 
	 * save the data into database.
	 * Finally export data to CVS file
	 * @param request
	 * @return
	 */
	@PostMapping("/search")
	public void search(@ModelAttribute("request") Request request, HttpServletResponse httpResponse) {
	    logger.info(String.valueOf(request.getLatitude()));
	    logger.info(String.valueOf(request.getLongitude()));

	    Optional.ofNullable(weatherService.getCurrentWeather(request.getLatitude(), request.getLongitude()))
	            .map(Util::processMasterDetail)
	            .map(masterDetailService::saveMasterDetail)
	            .ifPresentOrElse(
	                master -> {
	                    logger.info(master.toString());
	                    try {
	                        masterDetailService.exportDetailsToCsv(httpResponse);
	                    } catch (IOException e) {
	                        logger.error(e.getMessage());
	                    }
	                },
	                this::error
	            );

	    this.index();
	}
}
