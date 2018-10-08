package com.casestudy.forecast.weather.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties
public class DateTime {
    @ApiModelProperty(notes = "Date time of the temperature in epoch format ")
    long dt;
    @ApiModelProperty(notes = "Defines the temperature and pressure reading of the city ")
    Main main;
    @ApiModelProperty(notes = "Date time as text ")
    String dt_txt;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
