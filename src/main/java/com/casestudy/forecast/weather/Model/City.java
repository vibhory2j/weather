package com.casestudy.forecast.weather.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties
public class City {
    @ApiModelProperty(notes = "Name of the city")
    private String name;
    @ApiModelProperty(notes = "Country code of the city")
    private String country;
    @ApiModelProperty(notes = "Population of the city")
    private String population;
    @ApiModelProperty(notes = "Coordinates of the city in terms of lattiude and longitude")
    private Coordinates coordinates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @JsonSetter("coord")
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String toString() {
        return "name: " + name + " country:" + country
                + " population:" + population;
    }
}
