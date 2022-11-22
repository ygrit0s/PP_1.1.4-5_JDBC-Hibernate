package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Main {
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {

        userService.createUsersTable();

        userService.saveUser("Igor", "Atokhojaev", (byte) 36);
        userService.saveUser("Angela", "Lee", (byte) 28);
        userService.saveUser("Solomon", "Atokhojaev", (byte) 5);
        userService.saveUser("Maryann", "Atokhojaeva", (byte) 3);

        userService.getAllUsers().forEach(user -> LOGGER.info(user.toString()));

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
