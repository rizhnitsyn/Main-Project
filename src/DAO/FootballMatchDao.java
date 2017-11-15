package DAO;

import entities.FootballMatch;

import java.util.List;

public interface FootballMatchDao {
    boolean addMatch(FootballMatch footballMatch);
    boolean updateMatch(FootballMatch footballMatch);
    FootballMatch getMatchById(Long matchId);
    List<FootballMatch> getListOfMatches();
}
