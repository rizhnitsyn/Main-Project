package DAO;

import entities.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User user);
    boolean updateUser(User user);
    User getUserById(Long userId);
    List<User> getListOfUsers();
}
