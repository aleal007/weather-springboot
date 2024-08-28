package com.kapsch.weather.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.kapsch.weather.backend.model.WeatherResponse;

class WeatherServiceImplTest {

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Mock
    private RestTemplate restTemplate;

    @Value("${app.url}")
    private String url;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherService = new WeatherServiceImpl(restTemplate);
    }

    @Test
    void testGetCurrentWeatherSuccess() {
        double latitude = 52.52;
        double longitude = 13.41;
        String expectedUrl = url+"latitude=52.52&longitude=13.41&current_weather=true";

        WeatherResponse mockResponse = new WeatherResponse();
        when(restTemplate.getForObject(expectedUrl, WeatherResponse.class)).thenReturn(mockResponse);

        WeatherResponse response = weatherService.getCurrentWeather(latitude, longitude);

        assertNotNull(response);
        verify(restTemplate, times(1)).getForObject(expectedUrl, WeatherResponse.class);
    }

    @Test
    void testGetCurrentWeatherReturnsNullWhenNoResponse() {
        double latitude = 52.52;
        double longitude = 13.41;
        String expectedUrl = url+"latitude=52.52&longitude=13.41&current_weather=true";

        when(restTemplate.getForObject(expectedUrl, WeatherResponse.class)).thenReturn(null);

        WeatherResponse response = weatherService.getCurrentWeather(latitude, longitude);

        assertNull(response);
        verify(restTemplate, times(1)).getForObject(expectedUrl, WeatherResponse.class);
    }

    @Test
    void testGetCurrentWeatherHandlesRestTemplateException() {
        double latitude = 52.52;
        double longitude = 13.41;
        String expectedUrl = url+"latitude=52.52&longitude=13.41&current_weather=true";

        when(restTemplate.getForObject(expectedUrl, WeatherResponse.class)).thenThrow(new RuntimeException("API error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            weatherService.getCurrentWeather(latitude, longitude);
        });

        assertEquals("API error", exception.getMessage());
        verify(restTemplate, times(1)).getForObject(expectedUrl, WeatherResponse.class);
    }
}
