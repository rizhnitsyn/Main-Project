package Entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class FootballMatch extends IdentifiableEntity {
    private int firstTeamResult;
    private int secondTeamResult;
    private Date matchDateTime;
    private int matchState;
    private int matchType;
    private Long firstTeamId;
    private Long secondTeamId;
    private Tournament tournament;
    private Set<Forecast> forecasts  = new HashSet<>();
}
