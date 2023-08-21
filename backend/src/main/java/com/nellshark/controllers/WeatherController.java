package com.nellshark.controllers;

import com.nellshark.models.Weather;
import com.nellshark.services.WeatherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController extends AbstractGenericController<Weather> {

  public WeatherController(WeatherService service) {
    super(service);
  }
}
