package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
//import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {
//        Util util = new Util();

        userService.createUsersTable();

        userService.saveUser("Igor", "Atokhojaev", (byte) 36);
        userService.saveUser("Angela", "Lee", (byte) 28);
        userService.saveUser("Solomon", "Atokhojaev", (byte) 5);
        userService.saveUser("Maryann", "Atokhojaeva", (byte) 3);

        userService.getAllUsers().forEach(user -> System.out.println(user.toString()));

//        userService.removeUserById(id);

//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
