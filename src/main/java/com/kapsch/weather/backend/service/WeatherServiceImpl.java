package com.kapsch.weather.backend.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kapsch.weather.backend.model.WeatherResponse;

@Service
public class WeatherServiceImpl implements IWeatherService {
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Value("${app.url}")
	private String url;
	
	private final RestTemplate restTemplate;

    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Consume API REST JSON to get current weather
     */
    @Override
    public WeatherResponse getCurrentWeather(double latitude, double longitude) {
        String endpoint = Stream.of(
                this.url,
                "latitude=", String.valueOf(latitude),
                "&longitude=", String.valueOf(longitude),
                "&current_weather=true"
            )
            .collect(Collectors.joining());

        logger.info(endpoint);

        return restTemplate.getForObject(endpoint, WeatherResponse.class);
    }


}
