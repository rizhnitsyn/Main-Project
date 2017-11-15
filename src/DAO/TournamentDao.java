package DAO;

import entities.Tournament;

import java.util.List;

public interface TournamentDao {
    boolean addTournament(Tournament tournament);
    boolean updateTournament(Tournament tournament);
    Tournament getTournamentById(Long tournamentId);
    List<Tournament> getListOfTournaments();
}
