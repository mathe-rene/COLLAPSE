package de.cbm.fiae2025.cd.dao;

import de.cbm.fiae2025.cd.domain.User;

import java.sql.*;

public class UserDAO {
    private static final String SQL_CREATE = "insert into cd_user values (?,?,?,?)";
    private static final String SQL_READ = "select * from cd_user where id = ?";
    private static final String SQL_UPDATE = "update cd_user set user_name = ?, email = ?, password = ?, is_admin = ? where id = ?";
    private static final String SQL_DELETE = "delete from cd_user where id = ?";

    public void createPerson(User user) {
        try (
                Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBoolean(4, user.getAdmin());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                    }
                } catch (SQLException e) {
                    throw new DataAccessException("Es gab einen Datenbankfehler", e);

                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

class   DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}