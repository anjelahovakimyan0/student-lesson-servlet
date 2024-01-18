package org.example.studentlessonservlet.manager;

import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.model.User;

import java.sql.*;

public class UserManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addUser(User user) {
        String sql = "INSERT INTO user(name,surname,email,password) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = '" + email + "'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(email)
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = " + id;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return User.builder()
                        .id(id)
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
