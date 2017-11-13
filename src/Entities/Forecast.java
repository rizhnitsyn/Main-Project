package Entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Forecast extends IdentifiableEntity {
    private int firstTeamForecast;
    private int secondTeamForecast;
    private User user;
    private FootballMatch footballMatch;
}
