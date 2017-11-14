package DAO;

import Entities.Tournament;
import jdk.management.resource.internal.TotalResourceContext;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TournamentDaoTest {
    @Test
    public void testAddTournament() throws ParseException, SQLException {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        Random random = new Random();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        Date parsedDate = formatter.parse("10/06/2018");
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
        boolean saved = tournamentDao.addTournament(new Tournament(String.valueOf(random.nextInt(1000000)),
                 2L, sqlDate, 2L));
        Assert.assertTrue(saved);
    }

    @Test
    public void testGetTournamentById() {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        Tournament tournament = tournamentDao.getTournamentById(1L);
        Assert.assertNotNull(tournament);
        Assert.assertEquals("Чемпионат Европы 2016", tournament.getName());
    }

    @Test
    public void testUpdateTournament()  {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        Random random = new Random();
        Tournament tournament = tournamentDao.getTournamentById(2L);
        tournament.setName("updated" + String.valueOf(random.nextInt(1000000)));
        boolean updated = tournamentDao.updateTournament(tournament);
        Assert.assertNotNull(tournament);
        Assert.assertTrue(updated);
    }

    @Test
    public void getListOfTournaments() {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        List<Tournament> tournaments = tournamentDao.getListOfTournaments();
        Assert.assertNotNull(tournaments);
        Assert.assertTrue(tournaments.size() > 0);
    }
}
