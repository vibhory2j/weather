package com.casestudy.forecast.weather.Controller;

import com.casestudy.forecast.weather.Model.Response.Stats;
import com.casestudy.forecast.weather.Service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class defines the Rest Controller.
 * @author Vibhor Goyal
 *
 */
@RestController
@RequestMapping(value = "/api/v1/")
@Api(value="weatherforecast", description="Operations pertaining to retrieving the average weather forecasts")
public class DataController {

    private DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @ApiOperation(value = "View average day temperature in Celcius (06h-18h), average night temperature in Celcius (18h-06h) and average pressure for next three days for a city", response = Stats.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the results either from cache or from open map weather"),
            @ApiResponse(code = 404, message = "City not found")
    })
    @RequestMapping(value = "data", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Stats> returnData(@RequestParam (value = "city") String city) {

        //TODO : Check for injection attacks

        return dataService.returnAverageForecaseOfCity(city);
    }
}
