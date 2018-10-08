package com.casestudy.forecast.weather.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

/**
 * Describes lattitude and longitude coordinates of a location or city.
 */
@JsonIgnoreProperties()
public class Coordinates {
    @ApiModelProperty(notes = "Lattitude coordinates of the city ")
    private float lat;
    @ApiModelProperty(notes = "Longitude coordinates of the city ")
    private float lon;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
