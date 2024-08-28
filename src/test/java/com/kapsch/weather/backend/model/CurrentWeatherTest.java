package com.kapsch.weather.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentWeatherTest {

    @Test
    public void testGetAndSetTime() {
        CurrentWeather currentWeather = new CurrentWeather();
        String time = "2024-08-28T10:00:00Z";
        currentWeather.setTime(time);
        assertEquals(time, currentWeather.getTime());
    }

    @Test
    public void testGetAndSetInterval() {
        CurrentWeather currentWeather = new CurrentWeather();
        int interval = 60;
        currentWeather.setInterval(interval);
        assertEquals(interval, currentWeather.getInterval());
    }

    @Test
    public void testGetAndSetTemperature() {
        CurrentWeather currentWeather = new CurrentWeather();
        double temperature = 22.5;
        currentWeather.setTemperature(temperature);
        assertEquals(temperature, currentWeather.getTemperature());
    }

    @Test
    public void testGetAndSetWindspeed() {
        CurrentWeather currentWeather = new CurrentWeather();
        double windspeed = 15.5;
        currentWeather.setWindspeed(windspeed);
        assertEquals(windspeed, currentWeather.getWindspeed());
    }

    @Test
    public void testGetAndSetWinddirection() {
        CurrentWeather currentWeather = new CurrentWeather();
        int winddirection = 180;
        currentWeather.setWinddirection(winddirection);
        assertEquals(winddirection, currentWeather.getWinddirection());
    }

    @Test
    public void testGetAndSetIsDay() {
        CurrentWeather currentWeather = new CurrentWeather();
        int isDay = 1;
        currentWeather.setIs_day(isDay);
        assertEquals(isDay, currentWeather.getIs_day());
    }

    @Test
    public void testGetAndSetWeathercode() {
        CurrentWeather currentWeather = new CurrentWeather();
        int weathercode = 800;
        currentWeather.setWeathercode(weathercode);
        assertEquals(weathercode, currentWeather.getWeathercode());
    }
}

