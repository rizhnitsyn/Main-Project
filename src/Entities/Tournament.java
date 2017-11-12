package Entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Tournament {
    private Long id;
    private String name;
    private Date startDate;
    private Long organizerId;
    private Long stateId;

    public Tournament(String name, Date startDate, Long organizerId, Long stateId) {
        this.name = name;
        this.startDate = startDate;
        this.organizerId = organizerId;
        this.stateId = stateId;
    }
}
