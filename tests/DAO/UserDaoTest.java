package DAO;

import Entities.Tournament;
import Entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class UserDaoTest {

    @Test
    public void testAddTournament() {
        UserDao userDao = UserDao.getInstance();
        Random random = new Random();
        boolean saved = userDao.addUser(new User("Ivan", String.valueOf(random.nextInt(1000000)), "test" + String.valueOf(random.nextInt(100000)) + "@gmail.com"));
        Assert.assertTrue(saved);
    }

    @Test
    public void testGetUserById() {
        UserDao userDao = UserDao.getInstance();
        User user = userDao.getUserById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals("Рижницын", user.getSecondName());
    }

    @Test
    public void testUpdateUser()  {
        UserDao userDao = UserDao.getInstance();
        Random random = new Random();
        User user = userDao.getUserById(11L);
        user.setFirstName("updated" + String.valueOf(random.nextInt(1000000)));
        boolean updated = userDao.updateUser(user);
        Assert.assertNotNull(user);
        Assert.assertTrue(updated);
    }

    @Test
    public void getListOfUsers() {
        UserDao userDao = UserDao.getInstance();
        List<User> users = userDao.getListOfUsers();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
    }
}
