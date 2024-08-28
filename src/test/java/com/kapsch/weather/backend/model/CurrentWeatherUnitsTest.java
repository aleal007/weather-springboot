package com.kapsch.weather.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentWeatherUnitsTest {

    @Test
    public void testGetAndSetTime() {
        CurrentWeatherUnits currentWeatherUnits = new CurrentWeatherUnits();
        String time = "hours";
        currentWeatherUnits.setTime(time);
        assertEquals(time, currentWeatherUnits.getTime());
    }

    @Test
    public void testGetAndSetInterval() {
        CurrentWeatherUnits currentWeatherUnits = new CurrentWeatherUnits();
        String interval = "minutes";
        currentWeatherUnits.setInterval(interval);
        assertEquals(interval, currentWeatherUnits.getInterval());
    }

    @Test
    public void testGetAndSetTemperature() {
        CurrentWeatherUnits currentWeatherUnits = new CurrentWeatherUnits();
        String temperature = "Celsius";
        currentWeatherUnits.setTemperature(temperature);
        assertEquals(temperature, currentWeatherUnits.getTemperature());
    }

    @Test
    public void testGetAndSetWindspeed() {
        CurrentWeatherUnits currentWeatherUnits = new CurrentWeatherUnits();
        String windspeed = "km/h";
        currentWeatherUnits.setWindspeed(windspeed);
        assertEquals(windspeed, currentWeatherUnits.getWindspeed());
    }

    @Test
    public void testGetAndSetWinddirection() {
        CurrentWeatherUnits currentWeatherUnits = new CurrentWeatherUnits();
        String winddirection = "degrees";
        currentWeatherUnits.setWinddirection(winddirection);
        assertEquals(winddirection, currentWeatherUnits.getWinddirection());
    }

    @Test
    public void testGetAndSetIsDay() {
        CurrentWeatherUnits currentWeatherUnits = new CurrentWeatherUnits();
        String isDay = "boolean";
        currentWeatherUnits.setIs_day(isDay);
        assertEquals(isDay, currentWeatherUnits.getIs_day());
    }

    @Test
    public void testGetAndSetWeathercode() {
        CurrentWeatherUnits currentWeatherUnits = new CurrentWeatherUnits();
        String weathercode = "code";
        currentWeatherUnits.setWeathercode(weathercode);
        assertEquals(weathercode, currentWeatherUnits.getWeathercode());
    }
}
