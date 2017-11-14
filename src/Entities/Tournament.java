package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Tournament extends IdentifiableEntity{
    private String name;
    private Long organizerId;
    private Date startDate;
    private Long stateId;

    public Tournament(Long id, String name, Long organizerId, Date startDate, Long stateId) {
        super(id);
        this.name = name;
        this.organizerId = organizerId;
        this.startDate = startDate;
        this.stateId = stateId;
    }
    //    private Set<Users> = new HashSet; // список пользователей зарагеных на турнир, переопределить hash и equals

}
