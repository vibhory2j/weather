package com.casestudy.forecast.weather.Model.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties
public class ForecastStats {
    @ApiModelProperty(notes = "Date of the day")
    private String date;
    @ApiModelProperty(notes = "Average day temperature (06h - 18h)")
    private float avgDayTemp;
    @ApiModelProperty(notes = "Average night temperature (18h - 06h)")
    private float avgNightTemp;
    @ApiModelProperty(notes = "Average pressure throughout the day")
    private double avgPressure;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAvgDayTemp() {
        return avgDayTemp;
    }

    public void setAvgDayTemp(float avgDayTemp) {
        this.avgDayTemp = avgDayTemp;
    }

    public float getAvgNightTemp() {
        return avgNightTemp;
    }

    public void setAvgNightTemp(float avgNightTemp) {
        this.avgNightTemp = avgNightTemp;
    }

    public double getAvgPressure() {
        return avgPressure;
    }

    public void setAvgPressure(double avgPressure) {
        this.avgPressure = avgPressure;
    }

    public String toString() {
        return "date:" + date + " avgDayTemp:" + avgDayTemp + " avgNightTemp:" + avgNightTemp
                + " avgPressure:" + avgPressure;
    }
}
