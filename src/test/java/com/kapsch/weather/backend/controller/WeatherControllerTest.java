package com.kapsch.weather.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kapsch.weather.backend.dto.MasterDetailDto;
import com.kapsch.weather.backend.entity.Detail;
import com.kapsch.weather.backend.entity.Master;
import com.kapsch.weather.backend.model.CurrentWeather;
import com.kapsch.weather.backend.model.CurrentWeatherUnits;
import com.kapsch.weather.backend.model.Request;
import com.kapsch.weather.backend.model.WeatherResponse;
import com.kapsch.weather.backend.service.IMasterDetailsService;
import com.kapsch.weather.backend.service.IWeatherService;
import com.kapsch.weather.backend.util.Util;

import jakarta.servlet.http.HttpServletResponse;

class WeatherControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(WeatherControllerTest.class);

    @InjectMocks
    private WeatherController weatherController;

    @Mock
    private IWeatherService weatherService;

    @Mock
    private IMasterDetailsService masterDetailService;

    @Mock
    private HttpServletResponse httpResponse;
    
    @Mock
    private Util util;

    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        when(httpResponse.getWriter()).thenReturn(writer);
    }

    @Test
    void testSearchWeatherAndSaveMasterDetailSuccess() throws IOException {
        Request request = new Request();
        request.setLatitude(52.52);
        request.setLongitude(13.41);

        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCurrent_weather(new CurrentWeather());
        weatherResponse.setCurrent_weather_units(new CurrentWeatherUnits());
        Master master = new Master();

        when(weatherService.getCurrentWeather(request.getLatitude(), request.getLongitude()))
                .thenReturn(weatherResponse);
        when(masterDetailService.saveMasterDetail(any())).thenReturn(master);

        weatherController.search(request, httpResponse);

        verify(weatherService, times(1)).getCurrentWeather(request.getLatitude(), request.getLongitude());
        verify(masterDetailService, times(1)).saveMasterDetail(any());
        verify(masterDetailService, times(1)).exportDetailsToCsv(httpResponse);
    }

    @Test
    void testSearchWeatherNoResponse() throws IOException {
        Request request = new Request();
        request.setLatitude(52.52);
        request.setLongitude(13.41);

        when(weatherService.getCurrentWeather(request.getLatitude(), request.getLongitude()))
                .thenReturn(null);

        weatherController.search(request, httpResponse);

        verify(weatherService, times(1)).getCurrentWeather(request.getLatitude(), request.getLongitude());
        verify(masterDetailService, never()).saveMasterDetail(any());
        verify(masterDetailService, never()).exportDetailsToCsv(httpResponse);
    }
}
