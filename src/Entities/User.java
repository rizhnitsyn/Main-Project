package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class User extends IdentifiableEntity{
    private String firstName;
    private String secondName;
    private String email;
//    private Set<Tournament> tournaments = new HashSet<>();
//    private Set<Forecast> forecasts = new HashSet<>();

    public User(Long id, String firstName, String secondName, String email) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }
}
