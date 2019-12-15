package dz_15.dao;

import dz_15.pojo.Role;

public interface RoleDao {
    /**
     * Добавить роль в бд
     *
     * @param role
     * @return
     */
    public boolean addRole(Role role);

    /**
     * Получить роль из бд
     *
     * @param id
     * @return
     */
    public Role getRoleById(int id);

    /**
     * Обновить состояние роли в базе
     *
     * @param role
     * @return
     */
    public boolean updateRoleById(Role role);

    /**
     * Удалить роль
     *
     * @param id
     * @return
     */
    public boolean deleteRoleById(int id);

    public boolean initDefaultRoles();
}
