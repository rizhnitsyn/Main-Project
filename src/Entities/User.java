package Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User extends IdentifiableEntity{
    private String firstName;
    private String secondName;
    private String email;
    private Set<Tournament> tournaments = new HashSet<>();



}
