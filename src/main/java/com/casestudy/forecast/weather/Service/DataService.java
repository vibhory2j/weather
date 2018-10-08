package com.casestudy.forecast.weather.Service;

import com.casestudy.forecast.weather.Model.DateTime;
import com.casestudy.forecast.weather.Model.Forecast;
import com.casestudy.forecast.weather.Model.Response.ForecastStats;
import com.casestudy.forecast.weather.Model.Response.Stats;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DataService {
    ResponseEntity<Stats> returnAverageForecaseOfCity(String city);
    ResponseEntity<Forecast> retrieveForecastFromOpenMap(String city);
    ForecastStats calculateAverageTemperatures(List<DateTime> list);

}
