package com.casestudy.forecast.weather.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties
public class Forecast implements Serializable{

    @ApiModelProperty(notes = "List of objects with weather forecast")
    private List<DateTime> list;
    @ApiModelProperty(notes = "List of object with city details")
    private City city;

    public List<DateTime> getList() {
        return list;
    }

    public void setList(List<DateTime> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
