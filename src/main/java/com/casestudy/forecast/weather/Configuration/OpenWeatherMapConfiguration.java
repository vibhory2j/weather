package com.casestudy.forecast.weather.Configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({OpenWeatherMapProperties.class})
public class OpenWeatherMapConfiguration {
}
