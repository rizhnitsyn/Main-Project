package DAO;

import Entities.Tournament;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class TournamentDaoTest {
    @Test
    public void testAddTournament() throws ParseException {
        TournamentDao tournamentDao = TournamentDao.getInstance();
        Random random = new Random();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        Date parsedDate = formatter.parse("10/06/2018");
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
        boolean savedToDb = tournamentDao.addTournament(new Tournament(String.valueOf(random.nextInt(1000000)),
                sqlDate, 2L, 2L));
        Assert.assertTrue(savedToDb);

    }
}
