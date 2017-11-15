package DAO;

import DAO.daoImplementation.UserDaoImpl;
import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class UserDaoTest {

    @Test
    public void testAddTournament() {
        UserDaoImpl userDaoImpl = UserDaoImpl.getInstance();
        Random random = new Random();
        boolean saved = userDaoImpl.addUser(new User("Ivan", String.valueOf(random.nextInt(1000000)), "test" + String.valueOf(random.nextInt(100000)) + "@gmail.com"));
        Assert.assertTrue(saved);
    }

    @Test
    public void testGetUserById() {
        UserDaoImpl userDaoImpl = UserDaoImpl.getInstance();
        User user = userDaoImpl.getUserById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals("Рижницын", user.getSecondName());
        user.getForecasts().forEach(forecast -> System.out.println(forecast.getFirstTeamForecast() + " " + forecast.getSecondTeamForecast()));
        user.getTournaments().forEach(tournament -> System.out.println(tournament.getName()));
    }

    @Test
    public void testUpdateUser()  {
        UserDaoImpl userDaoImpl = UserDaoImpl.getInstance();
        Random random = new Random();
        User user = userDaoImpl.getUserById(11L);
        user.setFirstName("updated" + String.valueOf(random.nextInt(1000000)));
        boolean updated = userDaoImpl.updateUser(user);
        Assert.assertNotNull(user);
        Assert.assertTrue(updated);
    }

    @Test
    public void getListOfUsers() {
        UserDaoImpl userDaoImpl = UserDaoImpl.getInstance();
        List<User> users = userDaoImpl.getListOfUsers();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
    }
}
