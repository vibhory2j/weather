package com.casestudy.forecast.weather.Service;

import com.casestudy.forecast.weather.Cache.Cache;
import com.casestudy.forecast.weather.Configuration.OpenWeatherMapProperties;
import com.casestudy.forecast.weather.Model.Response.Stats;
import com.casestudy.forecast.weather.Model.DateTime;
import com.casestudy.forecast.weather.Model.Forecast;
import com.casestudy.forecast.weather.Model.Response.ForecastStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main service class that defines business logic, caching layer for retrieving
 * weather forecast from OpenWeatherMap.org and providing the average statistics
 * throughout the day for next 3 days to the user.
 * @author Vibhor Goyal
 * @version 1.0
 * @since 07.10.2018
 */

@Service
public class DataServiceImpl implements DataService {

    private static final Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);

    private OpenWeatherMapProperties openWeatherMapProperties;
    private RestTemplate restTemplate;
    private Cache cachedResult;
    @Autowired
    DataServiceImpl(OpenWeatherMapProperties openWeatherMapProperties) {
        this.openWeatherMapProperties = openWeatherMapProperties;
        restTemplate = new RestTemplate();
        cachedResult = new Cache();
    }

    /**
     * Main business service which retrieve the weather information from OpenMapWeather.org and build average temperature in
     * celcius for next three days. Please note the response returned by openmapweather is for 5 days with data every 3 hours. Hence
     * average is performed for data between 6h-18h and 18h-6h accordingly. Assumption here is that the response is sorted
     * by the datetime field in the json result. Implements a simple in-memory cache using a concurrent hashmap.
     * @see Cache
     * @param city String parameter describes the name of the city for which average forecast is required.
     * @return Response Entity of Stats which describes the average temperature and pressure for next 3 days of
     * the city.
     */
    public ResponseEntity<Stats> returnAverageForecaseOfCity(String city) {

        Stats stats;

        //validate cache first
        stats = cachedResult.get(city);
        if( stats!= null) {
            logger.info("Results found in cache...");
            return new ResponseEntity<Stats>(stats, HttpStatus.OK);
        }

        ResponseEntity<Forecast> response = retrieveForecastFromOpenMap(city);
        stats = new Stats();
        ForecastStats forecastStats;
        List<ForecastStats> forecastStatsList = new ArrayList<>();

        //Return the response as it is if the status code is not OK
        if(response.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity(response.getStatusCode());
        }

        Forecast forecast = (Forecast) response.getBody();
        logger.info("Size of list - {}", forecast.getList().size());
        List<DateTime> list = forecast.getList();

        //Set the city
        stats.setCity(forecast.getCity());

        //Retrieve the statistics for a day
        LocalDate today = LocalDate.now();
        LocalDate current = today;
        List<DateTime> temp = new ArrayList<>();

        for(DateTime dateTime : list) {

            LocalDate date = Instant.ofEpochMilli(dateTime.getDt() * 1000).atZone(ZoneId.systemDefault()).toLocalDate();

            if(date.isAfter(current)) {

                forecastStats = calculateAverageTemperatures(temp);

                forecastStatsList.add(forecastStats);

                temp = new ArrayList<>();
                current = date;
            }
            else {
                temp.add(dateTime);
            }

            //Calculate average of only following 3 days of statistics
            if(date.compareTo(today) > 2) {
                break;
            }
        }

        stats.setForecastStats(forecastStatsList);

        cachedResult.put(city, stats);

        return new ResponseEntity<Stats>(stats, HttpStatus.OK);
    }

    /**
     * Calls the 5 day weather forecast API of openweathermap.org and returns the response. Please note the response returned
     * is for 5 days with data every 3 hours. Checkout the details at https://openweathermap.org/forecast5
     * @param city
     * @return An object Forecast which captures only the temperature and pressure information
     *         from the response into the Forecast object.
     * @see Forecast
     */
    public ResponseEntity<Forecast> retrieveForecastFromOpenMap(String city) {

        String API_URL = openWeatherMapProperties.getApiUrl() + "appid="
                + openWeatherMapProperties.getToken()
                + "&units=metric&q=" + city;
        Forecast forecast;
        HttpEntity<Forecast> httpEntity = new HttpEntity<Forecast>(new Forecast());
        logger.info("Retrieving weather forecast for next 5 days for city - {}", city);

        try{
            return restTemplate.exchange(API_URL, HttpMethod.GET, httpEntity, Forecast.class);
        } catch (HttpStatusCodeException e){
            logger.info("Retrieval unsuccessfull for  weather forecast of city - {}", city);
            e.printStackTrace();
            return new ResponseEntity<Forecast>(e.getStatusCode());
        }

    }

    public ForecastStats calculateAverageTemperatures(List<DateTime> list) {

        float avgDaily = 0, avgNightly = 0;
        double avgPressure = 0.0;
        int size = list.size();

        ForecastStats forecastStats = new ForecastStats();

        for(DateTime dateTime : list) {

            LocalDateTime time = Instant.ofEpochMilli(dateTime.getDt() * 1000).atZone(ZoneId.systemDefault()).toLocalDateTime();
            int hour = time.getHour();
            double pressure = dateTime.getMain().getPressure();
            float temp = dateTime.getMain().getTemp();

            //Calculate average of nightly temperature/pressure
            if ((hour >= 0 && hour <= 5) || (hour >= 18 && hour <= 23)) {
                avgNightly = avgNightly + temp;
            }

            //Calculate average of daily temperature/pressure
            if (hour >= 6 && hour <= 18) {
                avgDaily = avgDaily + temp;
            }

            //Calculate average of daily pressure
            avgPressure = avgPressure + pressure;
        }

        avgPressure = avgPressure / size;
        avgDaily = avgDaily / size;
        avgNightly = avgNightly / size;

        forecastStats.setAvgPressure(avgPressure);
        forecastStats.setAvgDayTemp(avgDaily);
        forecastStats.setAvgNightTemp(avgNightly);

        String date = Instant.ofEpochMilli(list.get(0).getDt() * 1000).atZone(ZoneId.systemDefault()).toLocalDate().toString();
        forecastStats.setDate(date);

        return forecastStats;
    }
}
