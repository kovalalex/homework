package dz_15.dao;

import dz_15.pojo.User;

import java.util.List;

public interface UserDao {
    public boolean addUser(User user);

    public User getUserById(int id);

    public boolean updateUserById(int id);

    public boolean deleteUserById(int id);

    public List<User> getUserByNameLogin_ID(String name, int login_id);

    public boolean addUserWithNewRole(User user);
}
