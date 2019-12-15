package dz_15.dao;

import dz_15.ConnectionManager.ConnectionManager;
import dz_15.ConnectionManager.ConnectionManagerJdbcImpl;
import dz_15.pojo.Role;
import dz_15.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean addUser(User user) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement ps1 = connection.prepareStatement(
                    "INSERT INTO dz_14.public.user VALUES (DEFAULT ,?,?,?,?,?,?) RETURNING id");
            ps1.setString(1, user.getName());
            ps1.setObject(2, user.getBirthday());
            ps1.setInt(3, user.getLogin_ID());
            ps1.setString(4, user.getCity());
            ps1.setString(5, user.getEmail());
            ps1.setString(6, user.getDescription());
            int newUserID = 0;
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                newUserID = rs.getInt(1);
            }
            PreparedStatement ps2 = connection.prepareStatement(
                    "INSERT INTO user_role VALUES (DEFAULT, ?, ?)");
            ps2.setInt(1, newUserID);
            ps2.setInt(2, user.getRole().getId());
            ps2.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User getUserById(int id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM dz_14.public.user u LEFT JOIN user_role ur on u.id = ur.user_id LEFT JOIN role r on ur.role_id = r.id WHERE u.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {

                System.out.println(resultSet.getMetaData().getColumnName(11));
                user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getObject(3, LocalDate.class),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        new Role(resultSet.getInt(11),
                                resultSet.getString(12),
                                resultSet.getString(13))
                );
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUserById(int id) {
        return false;
    }

    @Override
    public boolean deleteUserById(int id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM dz_14.public.user WHERE id = ?");
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<User> getUserByNameLogin_ID(String name, int login_id) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM dz_14.public.user u LEFT JOIN user_role ur on u.id = ur.user_id" +
                            " LEFT JOIN role r on ur.role_id = r.id" +
                            " WHERE u.name = ? AND u.\"login_ID\" = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, login_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                System.out.println(resultSet.getMetaData().getColumnName(11));
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getObject(3, LocalDate.class),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        new Role(resultSet.getInt(11),
                                resultSet.getString(12),
                                resultSet.getString(13))
                );
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addUserWithNewRole(User user) {
        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement ps1 = connection.prepareStatement(
                    "INSERT INTO dz_14.public.user VALUES (DEFAULT ,?,?,?,?,?,?) RETURNING id");
            ps1.setString(1, user.getName());
            ps1.setObject(2, user.getBirthday());
            ps1.setInt(3, user.getLogin_ID());
            ps1.setString(4, user.getCity());
            ps1.setString(5, user.getEmail());
            ps1.setString(6, user.getDescription());
            int newUserID = 0;
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                newUserID = rs.getInt(1);
            }
            /*TODO добавить транзакцию*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
