package com.casestudy.forecast.weather.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties
public class Main {
    @ApiModelProperty(notes = "Temperature of the city ")
    private float temp;
    @ApiModelProperty(notes = "Minimum temperature")
    private float tempMin;
    @ApiModelProperty(notes = "Maximum temperature")
    private float tempMax;
    @ApiModelProperty(notes = "Atmospheric pressure reading of the city")
    private double pressure;

    public float getTemp() {
        return temp;
    }

    @JsonSetter("temp")
    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTempMin() {
        return tempMin;
    }

    @JsonSetter("temp_min")
    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    @JsonSetter("temp_max")
    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    @JsonSetter("pressure")
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
