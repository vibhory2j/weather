package com.casestudy.forecast.weather.Controller;

import com.casestudy.forecast.weather.Model.City;
import com.casestudy.forecast.weather.Model.Forecast;
import com.casestudy.forecast.weather.Model.Response.ForecastStats;
import com.casestudy.forecast.weather.Model.Response.Stats;
import com.casestudy.forecast.weather.Service.DataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DataController.class)
public class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataService dataService;

    @Test
    public void dataControllerTest() throws Exception{

        ForecastStats forecastStats = new ForecastStats();
        forecastStats.setDate("2018-08-20");
        forecastStats.setAvgNightTemp(2.08f);
        forecastStats.setAvgDayTemp(5.5f);
        forecastStats.setAvgPressure(1200.89 );

        City city = new City();
        city.setName("Berlin");
        city.setCountry("DE");
        city.setPopulation("100000");

        Stats stats = new Stats();
        List<ForecastStats> list = new ArrayList<>();
        list.add(forecastStats);
        stats.setForecastStats(list);
        stats.setCity(city);

        when(dataService.returnAverageForecaseOfCity("Berlin"))
                .thenReturn(new ResponseEntity<Stats>(stats, HttpStatus.OK));

        when(dataService.returnAverageForecaseOfCity("abc"))
                .thenReturn(new ResponseEntity<Stats>(new Stats(), HttpStatus.NOT_FOUND));

        //Test Http 200
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/data?city=Berlin")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andReturn();

        //Test Http 404
        request = MockMvcRequestBuilders
                .get("/api/v1/data?city=abc")
                .accept(MediaType.APPLICATION_JSON);

        result = mockMvc
                .perform(request)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }


    //TODO: units tests to validate average temperatures are calculated properly


}
