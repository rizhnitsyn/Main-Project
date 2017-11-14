package Entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Tournament {
    private Long id;
    private String name;
    private Date startDate;
    private Long organizerId;
    private Long stateId;
//    private Set<Users> = new HashSet; // список пользователей зарагеных на турнир, переопределить hash и equals

    public Tournament(String name, Date startDate, Long organizerId, Long stateId) {
        this.name = name;
        this.startDate = startDate;
        this.organizerId = organizerId;
        this.stateId = stateId;
    }
}
