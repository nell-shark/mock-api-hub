package com.nellshark.services;

import com.nellshark.models.Weather;
import com.nellshark.repositories.WeatherRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherService extends AbstractGenericService<Weather> {

  public WeatherService(WeatherRepository weatherRepository) {
    super(weatherRepository);
  }
}
