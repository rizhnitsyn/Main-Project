package entities;

import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"footballMatches", "users", "stateId"})
public class Tournament {
    private Long id;
    private String name;
    private int organizerId;
    private Date startDate;
    private int stateId;
    private Set<FootballMatch> footballMatches = new HashSet<>();
    private Set<User> users = new HashSet<>();

    public Tournament(String name, int organizerId, Date startDate, int stateId) {
        this.name = name;
        this.organizerId = organizerId;
        this.startDate = startDate;
        this.stateId = stateId;
    }

    public Tournament(Long id, String name, int organizerId, Date startDate, int stateId) {
        this(name, organizerId, startDate, stateId);
        this.id = id;
    }

    public void addFootballMatch(FootballMatch footballMatch) {
        footballMatches.add(footballMatch);
    }

    public void addUser(User user) {
        users.add(user);
    }

}
