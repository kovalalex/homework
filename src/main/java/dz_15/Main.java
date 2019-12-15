package dz_15;

import dz_15.dao.RoleDao;
import dz_15.dao.RoleDaoImpl;
import dz_15.dao.UserDao;
import dz_15.dao.UserDaoImpl;
import dz_15.pojo.Role;
import dz_15.pojo.User;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Role admRole = new Role(null, "Administration", "1 adm");
        Role clientsRole = new Role(null, "Clients", "2 cl");
        RoleDao roleDao = new RoleDaoImpl();
        roleDao.initDefaultRoles();
        UserDao userDao = new UserDaoImpl();
        //  roleDao.addRole(admRole);
        //  roleDao.addRole(clientsRole);
        // System.out.println(roleDao.getRoleById(2));
        User client = new User(null, "Вася", LocalDate.now(), 3, "1@1.ru",
                "Innopolis", "Вася из иннополиса", roleDao.getRoleById(1));
        userDao.addUser(client);
        System.out.println(userDao.getUserById(1));
        List<User> userList = userDao.getUserByNameLogin_ID("Вася", 3);
        //userDao.deleteUserById(1);
        if (userList.isEmpty()) System.out.println("нет таких пользователей");
        userList.forEach(user -> System.out.println(user));


    }
}
