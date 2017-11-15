package DAO;

import DAO.daoImplementation.FootballMatchDaoImpl;
import entities.FootballMatch;
import entities.Tournament;
import org.junit.Assert;
import org.junit.Test;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class FootballMatchDaoTest {

    @Test
    public void testAddFootballMatch() {
        FootballMatchDaoImpl footballMatchDaoImpl = FootballMatchDaoImpl.getInstance();
        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
        Tournament tournament = new Tournament();
        tournament.setId(2L);
        boolean saved = footballMatchDaoImpl.addMatch(new FootballMatch(sqlDate, 1, 1, 1, 2, tournament));
        Assert.assertTrue(saved);
    }

    @Test
    public void testGetFootballMatchById() {
        FootballMatchDaoImpl footballMatchDaoImpl = FootballMatchDaoImpl.getInstance();
        FootballMatch footballMatch = footballMatchDaoImpl.getMatchById(1L);
        Assert.assertNotNull(footballMatch);
        Assert.assertEquals(2, footballMatch.getSecondTeamId());
//        System.out.println(footballMatch);
        System.out.println(footballMatch.getTournament().getName());
        footballMatch.getForecasts().forEach(forecast -> System.out.println(forecast.getId()));
    }

    @Test
    public void testUpdateFootballMatch()  {
        FootballMatchDaoImpl footballMatchDaoImpl = FootballMatchDaoImpl.getInstance();
        Random random = new Random();
        FootballMatch footballMatch = footballMatchDaoImpl.getMatchById(37L);
        int matchResult = random.nextInt(1000000);
        footballMatch.setFirstTeamResult(matchResult);
        boolean updated = footballMatchDaoImpl.updateMatch(footballMatch);
        FootballMatch newMatch = footballMatchDaoImpl.getMatchById(37L);
        Assert.assertNotNull(footballMatch);
        Assert.assertTrue(updated);
        Assert.assertEquals(matchResult, (int) newMatch.getFirstTeamResult());
    }

    @Test
    public void getListOfFootballMatches() {
        List<FootballMatch> footballMatches = FootballMatchDaoImpl.getInstance().getListOfMatches();
        Assert.assertNotNull(footballMatches);
        Assert.assertTrue(footballMatches.size() > 0);
    }
}
