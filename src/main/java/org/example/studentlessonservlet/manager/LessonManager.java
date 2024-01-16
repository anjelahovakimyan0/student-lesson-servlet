package org.example.studentlessonservlet.manager;

import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.model.Lesson;
import org.example.studentlessonservlet.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addLesson(Lesson lesson) {
        String sql = "INSERT INTO lesson(name,duration,lecturer_name,price) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, DateUtil.dateToString(lesson.getDuration()));
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                lesson.setId(id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getAllLessons() {
        String sql = "SELECT * FROM lesson";
        List<Lesson> lessons = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                lessons.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(DateUtil.stringToDate(resultSet.getString("duration")))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM lesson WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lesson getById(int id) {
        String sql = "SELECT * FROM lesson WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Lesson.builder()
                        .id(id)
                        .name(resultSet.getString("name"))
                        .duration(DateUtil.stringToDate(resultSet.getString("duration")))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Lesson lesson) {
        String sql = "UPDATE lesson SET name = ?, duration = ?, lecturer_name = ?, price = ? WHERE id=" + lesson.getId();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, DateUtil.dateToString(lesson.getDuration()));
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
