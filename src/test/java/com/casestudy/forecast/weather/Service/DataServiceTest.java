package com.casestudy.forecast.weather.Service;

import com.casestudy.forecast.weather.Configuration.OpenWeatherMapProperties;
import com.casestudy.forecast.weather.Model.Response.Stats;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class DataServiceTest {

    OpenWeatherMapProperties openWeatherMapProperties = new OpenWeatherMapProperties(
            "http://api.openweathermap.org/data/2.5/forecast?"
            , "c49767fe567a806a0420fd6196f1ad7a");


    @Test
    public void returnAverageForecaseOfCityTest() {
    DataServiceImpl dataService = new DataServiceImpl(openWeatherMapProperties);

    ResponseEntity<Stats> response = dataService.returnAverageForecaseOfCity("Berlin");
    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody().getCity().toString()).contains("Berlin");
    }
}
