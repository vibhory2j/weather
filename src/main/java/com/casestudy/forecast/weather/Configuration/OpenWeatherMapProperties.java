package com.casestudy.forecast.weather.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "open.weather")
public class OpenWeatherMapProperties {

    private String apiUrl;
    private String token;

    public OpenWeatherMapProperties() {

    }

    public OpenWeatherMapProperties(String apiUrl, String token) {
        this.apiUrl = apiUrl;
        this.token = token;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
