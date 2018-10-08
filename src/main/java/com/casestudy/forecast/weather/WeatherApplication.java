package com.casestudy.forecast.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * This application exposes a REST API controller which provides average temperatures of a city.
 * @author Vibhor Goyal vibhory2j@gmail.com
 * @version 1.0
 * @since 05.10.2018
 */
@SpringBootApplication
@EnableCaching
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}
}
