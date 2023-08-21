package com.nellshark.repositories;

import com.nellshark.models.Weather;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository extends AbstractGenericRepository<Weather> {

  public WeatherRepository() {
    super(List.of());
  }
}
