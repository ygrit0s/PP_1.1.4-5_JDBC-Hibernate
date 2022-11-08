package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    @Override
    public void createUsersTable() throws SQLException {
        Connection conn = Util.getConnection();
        String createSQL = """
                CREATE TABLE IF NOT EXISTS `mydb`.`user` (
                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                    `name` VARCHAR(32) NULL,
                    `lastName` VARCHAR(32) NULL,
                    `age` INT NULL,
                    PRIMARY KEY (`id`));""";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createSQL);
        }
    }

    @Override
    public void dropUsersTable() throws SQLException {
        Connection conn = Util.getConnection();
        String dropSQL = "DROP TABLE IF EXISTS user;";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(dropSQL);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection conn = Util.getConnection();
        String insertSQL = "INSERT INTO `user` (`name`, `lastName`, `age`) VALUES (?, ?, ?);";
        try (PreparedStatement prepStmt = conn.prepareStatement(insertSQL)) {
            prepStmt.setString(1, name);
            prepStmt.setString(2, lastName);
            prepStmt.setInt(3, age);
            prepStmt.executeUpdate();
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection conn = Util.getConnection();
        String deleteSQL = "DELETE FROM `user` WHERE `id` = ?;";
        try (PreparedStatement prepStmt = conn.prepareStatement(deleteSQL)) {
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection conn = Util.getConnection();
        String selectSQL = "SELECT * FROM `user`;";
        try (PreparedStatement prepStmt = conn.prepareStatement(selectSQL)) {
            ResultSet rSet = prepStmt.executeQuery();
            while (rSet.next()) {
                User user = new User();
                user.setId(rSet.getLong(1));
                user.setName(rSet.getString(2));
                user.setLastName(rSet.getString(3));
                user.setAge(rSet.getByte(4));
                userList.add(user);
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection conn = Util.getConnection();
        String truncateSQL = "TRUNCATE TABLE `user`;";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(truncateSQL);
        }
    }
}
