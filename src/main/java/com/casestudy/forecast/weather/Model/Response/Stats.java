package com.casestudy.forecast.weather.Model.Response;

import com.casestudy.forecast.weather.Model.City;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties
public class Stats implements Serializable{
    @ApiModelProperty(notes = "List of object with city details")
    private City city;
    @ApiModelProperty(notes = "List of weather forecasts")
    private List<ForecastStats> forecastStats;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<ForecastStats> getForecastStats() {
        return forecastStats;
    }

    public void setForecastStats(List<ForecastStats> forecastStats) {
        this.forecastStats = forecastStats;
    }
}
