package DAO;

import entities.Forecast;

import java.util.List;

public interface ForecastDao {
    boolean addForecast(Forecast forecast);
    boolean updateForecast(Forecast forecast);
    Forecast getForecastById(Long tournamentId);
    List<Forecast> getListOfForecasts();
}
