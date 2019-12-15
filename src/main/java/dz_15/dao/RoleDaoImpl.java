package dz_15.dao;

import dz_15.ConnectionManager.ConnectionManager;
import dz_15.ConnectionManager.ConnectionManagerJdbcImpl;
import dz_15.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoImpl implements RoleDao {
    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();

    /**
     * Добавить роль в бд
     *
     * @param role
     * @return
     */
    @Override
    public boolean addRole(Role role) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO role values (DEFAULT ,?,?)");
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Получить роль из бд
     *
     * @param id
     * @return
     */
    @Override
    public Role getRoleById(int id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM role WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Role role = new Role(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
                return role;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Обновить состояние роли в базе
     *
     * @param role
     * @return
     */
    @Override
    public boolean updateRoleById(Role role) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE role SET name=?, description = ? " +
                            "WHERE id = ?");
            preparedStatement.setInt(3, role.getId());
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Удалить роль
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteRoleById(int id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM role WHERE id = ?");
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean initDefaultRoles() {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO role values (DEFAULT ,?,?)");
            preparedStatement.setString(1, "Administration");
            preparedStatement.setString(2, "");
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Clients");
            preparedStatement.setString(2, "");
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Billing");
            preparedStatement.setString(2, "");
            preparedStatement.addBatch();
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
